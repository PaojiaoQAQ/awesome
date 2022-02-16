package com.example.demo.cos.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.example.demo.cos.pojo.dto.CosRequest;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.transfer.Download;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDateTime;

/**
 * @description
 * @author tanyue
 * @Date 2022/2/15
 **/
@Service
@Slf4j
public class CvmClientBiz
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

    public String uploadFile(CosRequest cosRequest)
    {
        File file = new File(cosRequest.getUploadFilePath());
        String fileKey = commonRootPath + "/" + LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.PURE_DATETIME_MS_PATTERN) + file.getName()
                .substring(file.getName().lastIndexOf("."));
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

    public void downloadFile(CosRequest cosRequest){
        File downloadFile = new File(cosRequest.getDownloadFilePath());
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, cosRequest.getFileKey());
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

    public String getCosFileUrl(CosRequest cosRequest){
        return commonCosClient.getObjectUrl(bucketName,cosRequest.getFileKey()).toString();
    }
}
