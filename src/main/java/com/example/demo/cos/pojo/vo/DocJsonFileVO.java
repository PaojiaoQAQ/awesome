package com.example.demo.cos.pojo.vo;

import lombok.Data;

/**
 * @description cos文件预览图片json文件
 * @author tanyue
 * @Date 2021/11/29
 **/
@Data
public class DocJsonFileVO
{
    /**
     * 图片cos fileKey
     */
    private String picFileKey;
    /**
     * 页码
     */
    private String pageNo;
}
