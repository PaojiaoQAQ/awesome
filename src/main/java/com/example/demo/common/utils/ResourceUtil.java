package com.example.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

public class ResourceUtil {
    public static final Logger logger = LoggerFactory.getLogger(ReadFileUtils.class);
    public static ResourceUtil resourceUtil = null;
    //默认读取application配置文件
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("appConfig");
    private ResourceUtil(){
    }
    public static ResourceUtil getInstance(String properties){
        if(resourceUtil == null){
            resourceUtil = new ResourceUtil();
        }
        if (properties != null){
            resourceBundle = ResourceBundle.getBundle(properties);
        }
        return resourceUtil;
    }
    public static String getAppconfigByKey(String key){
        String value = "";
        //
        try {
            value = new String(resourceBundle.getString(key).getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("string encode fail");
        }
        return value;
    }
}
