package com.example.demo.cos.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.example.demo.cos.constant.DocPreviewConstant;
import com.example.demo.cos.pojo.dto.CosRequest;
import com.example.demo.cos.pojo.vo.DocJsonFileVO;
import com.example.demo.cos.service.CvmClientBiz;
import com.example.demo.utils.JacksonJsonUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.internal.Unmarshallers;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.model.ciModel.common.MediaOutputObject;
import com.qcloud.cos.model.ciModel.job.DocJobDetail;
import com.qcloud.cos.model.ciModel.job.DocJobObject;
import com.qcloud.cos.model.ciModel.job.DocJobRequest;
import com.qcloud.cos.model.ciModel.job.DocJobResponse;
import com.qcloud.cos.model.ciModel.job.DocOperationObject;
import com.qcloud.cos.model.ciModel.job.DocProcessObject;
import com.qcloud.cos.model.ciModel.job.DocProcessPageInfo;
import com.qcloud.cos.model.ciModel.job.DocProcessResult;
import com.qcloud.cos.transfer.Download;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description
 * @author tanyue
 * @Date 2022/2/15
 **/
@Service
@Slf4j
public class CvmClientBizImpl implements CvmClientBiz
{
    @Resource(name = "commonCvmClient")
    private CvmClient cvmClient;
    @Resource(name = "transferManager")
    private TransferManager transferManager;
    @Value("${cos.common.bucket}")
    private String bucketName;
    @Value("${cos.common.rootPath}")
    private String commonRootPath;
    @Resource(name = "commonCosClient")
    private COSClient commonCosClient;
    @Value("${cos.common.preview_queue}")
    private String docPreviewQueue;
    @Value("${cos.common.region}")
    private String cosRegion;
    @Value("${download.tempFilePath}")
    private String tempFilePath;

    @Override
    public String uploadFile(File file,String fileKey)
    {
        PutObjectRequest request = new PutObjectRequest(bucketName, fileKey, file);
        try
        {
            Upload upload = transferManager.upload(request);
            UploadResult uploadResult = upload.waitForUploadResult();
            log.info("cos upload result:{}",uploadResult);
            return fileKey;
        }
        catch(Exception e)
        {
            log.error("上传cos异常",e);
        }

        return null;
    }

    @Override
    public void downloadFile(String fileKey,String downloadPath){
        File downloadFile = new File(downloadPath);
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, fileKey);
        try
        {
            Download download = transferManager.download(getObjectRequest, downloadFile);
            download.waitForCompletion();
        }
        catch(Exception e)
        {
            log.error("下载cos文件异常",e);
        }
    }

    @Override
    public String generateCosPresignedFileUrl(String fileKey){
        // 设置签名过期时间(可选), 若未进行设置则默认使用 ClientConfig 中的签名过期时间(1小时)
        // 这里设置签名在24小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
        // 填写本次请求的参数，需与实际请求相同，能够防止用户篡改此签名的 HTTP 请求的参数
        Map<String, String> params = new HashMap<>();
        params.put("param1", "value1");
        // 填写本次请求的头部，需与实际请求相同，能够防止用户篡改此签名的 HTTP 请求的头部
        Map<String, String> headers = new HashMap<>();
        headers.put("header1", "value1");
        // 请求的 HTTP 方法，上传请求用 PUT，下载请求用 GET，删除请求用 DELETE
        HttpMethodName method = HttpMethodName.GET;
        URL url = commonCosClient.generatePresignedUrl(bucketName, fileKey, expirationDate, method, headers, params);
        return url.toString();
    }

    @Override
    public Boolean ceateDocPreviewJob(CosRequest cosRequest){
        DocJobRequest request = buildDocJobRequest(cosRequest);
        //调用接口,获取任务响应对象
        DocJobResponse docProcessJob = commonCosClient.createDocProcessJobs(request);
        log.info("文件预览创建任务响应对象,docProcessJob:{}", docProcessJob.toString());
        String code = Optional.ofNullable(docProcessJob).map(DocJobResponse::getJobsDetail).map(DocJobDetail::getCode).orElse(null);
        return "Success".equals(code);
    }

    @Override
    public void dealDocJobCallBack(InputStream ins)
    {
        DocJobResponse response = null;
        try
        {
            response = new Unmarshallers.DescribeDocJobUnmarshaller().unmarshall(ins);
        }
        catch(Exception e)
        {
            log.warn("解析文件预览回调实体异常",e);
        }
        if(Objects.isNull(response)){
            return;
        }
        log.info("解析后DocJobResponse:{}",response);
        //根据回调结果生成json文件
        generateJsonFile(response);
    }
    @Override
    public List<String> getPreviewPicList(String tenantId, String businessType,String businessId)
    {
        String fileKey = String.format(DocPreviewConstant.PREVIEW_JSON_FILE_PATH_FORMAT,tenantId,businessType,businessId);
        String content = readJsonFile(fileKey);
        if(StringUtils.isEmpty(content)){
            log.info("预览json文件内容为空,fileKey:{}",fileKey);
            return new ArrayList<>();
        }
        List<DocJsonFileVO> docJsonFileVOList = JSON.parseArray(content, DocJsonFileVO.class);
        return docJsonFileVOList.stream()
                .map(item -> this.generateCosPresignedFileUrl(fileKey))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean isDuplicateMessage(String data)
    {
        //TODO 数据加密，redis校验幂等
        return false;
    }
    private void generateJsonFile(DocJobResponse response){
        DocProcessResult result = Optional.ofNullable(response.getJobsDetail())
                .map(DocJobDetail::getOperation)
                .map(DocOperationObject::getDocProcessResult)
                .orElse(null);
        if(Objects.isNull(result) || CollectionUtils.isEmpty(result.getDocProcessPageInfoList())){
            log.info("文件预览回调信息中没有docProcessResult或PageInfo信息!");
            return;
        }
        //先将生成的图片排序
        List<DocProcessPageInfo> docProcessPageInfoList = result.getDocProcessPageInfoList().stream()
                .sorted(Comparator.comparing(item -> Integer.valueOf(item.getPageNo())))
                .collect(Collectors.toList());
        List<DocJsonFileVO> docJsonFileVOList = new ArrayList<>();
        docProcessPageInfoList.stream().forEach(item ->{
            DocJsonFileVO fileVO = new DocJsonFileVO();
            fileVO.setPicFileKey(item.getTgtUri());
            fileVO.setPageNo(item.getPageNo());
            docJsonFileVOList.add(fileVO);
        });
        String picFileKey = docProcessPageInfoList.stream().findFirst().map(DocProcessPageInfo::getTgtUri).orElse(null);
        if(StringUtils.isBlank(picFileKey)){
            log.info("tgtUri为空，无法获取文件fileKey");
            return;
        }
        //生成json文件并上传腾讯云
        uploadJsonFile(picFileKey, JSON.toJSONString(docJsonFileVOList));
    }

    private void uploadJsonFile(String picFileKey,String context){
        String shortPath = picFileKey.substring(0,picFileKey.lastIndexOf("/"));
        String jsonFileDir = String.format("%s/%s",tempFilePath,shortPath);
        String jsonFileKey = String.format("%s/%s",shortPath,DocPreviewConstant.JSON_FILE_NAME);
        File dir = new File(jsonFileDir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        String localFilePath = String.format("%s/%s",jsonFileDir,DocPreviewConstant.JSON_FILE_NAME);
        File jsonFile = new File(localFilePath);
        writeFile(context,jsonFile);
        this.uploadFile(jsonFile,jsonFileKey);
    }
    private DocJobRequest buildDocJobRequest(CosRequest cosRequest){
        //1.创建任务请求对象
        DocJobRequest request = new DocJobRequest();
        //2.添加请求参数 参数详情请见api接口文档
        request.setBucketName(bucketName);
        DocJobObject docJobObject = request.getDocJobObject();
        docJobObject.setTag("DocProcess");
        docJobObject.getInput().setObject(cosRequest.getFileKey());
        docJobObject.setQueueId(docPreviewQueue);
        DocProcessObject docProcessObject = docJobObject.getOperation().getDocProcessObject();
        docProcessObject.setTgtType("jpg");
        MediaOutputObject output = docJobObject.getOperation().getOutput();
        output.setRegion(cosRegion);
        output.setBucket(bucketName);
        String previewFileDir = String.format(commonRootPath + "/" + "preview" + "/" +  LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.PURE_DATETIME_MS_PATTERN));
        output.setObject(previewFileDir + "/pic-${Page}.jpg");
        return request;
    }

    private void writeFile(String context,File file){
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file)))
        {
            outputStream.writeBytes(context);
        }
        catch(FileNotFoundException e)
        {
            log.warn("文件目录不存在:{}",file.getPath());
        }
        catch(IOException e)
        {
            log.warn("文件预览json文件写入异常",e);
        }
    }

    private String readJsonFile(String fileKey){
        COSObject jsonFile = commonCosClient.getObject(bucketName,fileKey);
        if(Objects.isNull(jsonFile)){
            return null;
        }
        String result = null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(jsonFile.getObjectContent())))
        {
            result = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
        catch(IOException e)
        {
            log.warn("cos读取json文件内容异常,fileKey:{}",fileKey);
        }
        return result;
    }
}
