package com.example.demo.cos.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description
 * @author tanyue
 * @Date 2022/2/16
 **/
@Data
public class CosRequest implements Serializable
{
    private String downloadFilePath;

    private String uploadFilePath;

    private String fileKey;
}
