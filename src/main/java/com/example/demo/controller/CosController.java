package com.example.demo.controller;

import com.example.demo.common.entity.Result;
import com.example.demo.cos.pojo.dto.CosRequest;
import com.example.demo.cos.service.CvmClientBiz;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description
 * @author tanyue
 * @Date 2022/2/16
 **/
@RestController
@RequestMapping("/cos")
public class CosController
{
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
        return new Result<String>().success(cvmClientBiz.uploadFile(request));
    }

    @PostMapping("/downloadFile")
    @ResponseBody
    public Result downloadFile(@RequestBody CosRequest request){
        cvmClientBiz.downloadFile(request);
        return new Result().success();
    }

    @PostMapping("/getFileUrl")
    @ResponseBody
    public Result getFileUrl(@RequestBody CosRequest request){
        return new Result<String>().success(cvmClientBiz.getCosFileUrl(request));
    }

}
