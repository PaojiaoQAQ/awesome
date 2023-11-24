package com.example.demo.cos.service;

import com.example.demo.cos.pojo.dto.CosRequest;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author tanyue
 *
 * @Date 2022/2/16
 **/
public interface CvmClientBiz
{
    String uploadFile(File file,String fileKey);

    void downloadFile(String fileKey,String downloadPath);

    String generateCosPresignedFileUrl(String fileKey);

    Boolean ceateDocPreviewJob(CosRequest cosRequest);

    void dealDocJobCallBack(InputStream ins);

    /**
     *
     * @param tenantId 企业id
     * @param businessType 业务类型 BusinessTypeEnum
     * @param businessId 业务id
     * @return
     */
    List<String> getPreviewPicList(String tenantId,String businessType,String businessId);

    /**
     * 消息幂等校验
     * @param data
     * @return
     */
    Boolean isDuplicateMessage(String data);
}
