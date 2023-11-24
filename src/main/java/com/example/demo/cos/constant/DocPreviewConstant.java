package com.example.demo.cos.constant;


/**
 * @description
 * @author tanyue
 * @Date 2021/11/29
 **/
public interface DocPreviewConstant
{
    /**
     * 文件预览json文件名
     */
    String JSON_FILE_NAME = "fileKey.json";
    /**
     * 文件预览job TAG
     */
    String CREATE_JOB_TAG = "DocProcess";
    /**
     * jpg
     */
    String PIC_FORMAT_JPG = "jpg";
    /**
     * png
     */
    String PIC_FORMAT_PNG = "png";
    /**
     * 文件预览根路径
     */
    String PREVIEW_ROOT_PATH = "doc_preview";
    /**
     * 文件预览原始文件腾讯云key格式 根路径/temp/模块名/文件名.文件类型
     */
    String TEMP_FILE_PATH_FORMAT = PREVIEW_ROOT_PATH + "/temp/%s/%s.%s";
    /**
     * 文件预览 预览文件文件夹路径 doc_preview/tenantId/businessType/businessId
     */
    String PREVIEW_FILE_PATH_FORMAT = PREVIEW_ROOT_PATH + "/%s/%s/%s";
    /**
     * jsonfile cos filekey
     */
    String PREVIEW_JSON_FILE_PATH_FORMAT = PREVIEW_FILE_PATH_FORMAT + "/" + JSON_FILE_NAME;
    /**
     * 腾讯任务执行成功返回状态
     */
    String SUCCESS_STATE = "Success";

}
