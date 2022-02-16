package com.example.demo.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.example.demo.common.entity.Result;
import com.example.demo.cos.pojo.dto.CosRequest;
import com.example.demo.cos.service.CvmClientBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @description
 * @author tanyue
 * @Date 2022/2/16
 **/
@RestController
@RequestMapping("/cos")
@Slf4j
public class CosController
{
    @Value("${cos.common.rootPath}")
    private String commonRootPath;

    @Resource
    private CvmClientBiz cvmClientBiz;

    @ResponseBody
    @RequestMapping("/checkHealth")
    public String checkHealth()
    {
        return "ok";
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public Result uploadFile(@RequestBody CosRequest request){
        File file = new File(request.getUploadFilePath());
        String fileKey = commonRootPath + "/" + LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.PURE_DATETIME_MS_PATTERN) + file.getName()
                .substring(file.getName().lastIndexOf("."));
        return new Result<String>().success(cvmClientBiz.uploadFile(file, fileKey));
    }

    @PostMapping("/downloadFile")
    @ResponseBody
    public Result downloadFile(@RequestBody CosRequest request){
        cvmClientBiz.downloadFile(request.getFileKey(),request.getDownloadFilePath());
        return new Result().success();
    }

    @PostMapping("/getFileUrl")
    @ResponseBody
    public Result getFileUrl(@RequestBody CosRequest request){
        return new Result<String>().success(cvmClientBiz.generateCosPresignedFileUrl(request.getFileKey()));
    }

    @PostMapping("/createDocJob")
    @ResponseBody
    public Result createDocJob(@RequestBody CosRequest request){
        return new Result<Boolean>().success(cvmClientBiz.ceateDocPreviewJob(request));
    }

    @RequestMapping(value = "/queueCallBack", headers = { "Accept=application/xml" })
    @ResponseBody
    public void previewQueueCallBack(@RequestBody String body){
        log.info("开始处理文件预览回调,xmlStr:{}",body);
        if(Objects.isNull(body)){
            return;
        }
        //TODO 消息幂等校验（redis）
        InputStream ins = new ByteArrayInputStream(body.getBytes());
        cvmClientBiz.dealDocJobCallBack(ins);
    }
}
