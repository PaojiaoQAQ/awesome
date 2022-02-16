package com.example.demo.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @author tanyue
 * @Date 2022/2/16
 **/
public class JacksonJsonUtils
{

    static ObjectMapper objectMapperCommon = new ObjectMapper();

    static
    {
        // 允许pojo中有在json串中不存在的字段
        objectMapperCommon.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许有注释
        objectMapperCommon.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    /**
     * 流转对象
     * @param inputStream
     * @param tClass
     * @return
     */
    public static <T> T parseObject(InputStream inputStream, Class<T> tClass)
    {

        Reader reader = new InputStreamReader(inputStream);

        try
        {
            return objectMapperCommon.readValue(reader, tClass);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 自定义解析规则，解析json对象
     * @param objectMapperCustomerlized
     * @param inputStream
     * @param tClass
     * @return
     */
    public static <T> T parseObjectCustomerlized(ObjectMapper objectMapperCustomerlized, InputStream inputStream,
            Class<T> tClass)
    {

        Reader reader = new InputStreamReader(inputStream);

        try
        {
            return objectMapperCustomerlized.readValue(reader, tClass);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * json string转 对象
     *
     * @param json
     * @param tClass
     * @return
     */
    public static <T> T parseObject(String json, Class<T> tClass)
    {
        try
        {
            return objectMapperCommon.readValue(json, tClass);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 自定义规则 吧json string转 对象
     * @param objectMapperCustomerlized
     * @param jsonStr
     * @param tClass
     * @return
     */
    public static <T> T parseObjectCustomerlized(ObjectMapper objectMapperCustomerlized, String jsonStr,
            Class<T> tClass)
    {
        try
        {
            return objectMapperCustomerlized.readValue(jsonStr, tClass);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象转json string
     * @param object
     * @return
     */
    public static String toJsonString(Object object)
    {
        try
        {
            return objectMapperCommon.writeValueAsString(object);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     *自定义规则 对象转json string
     * @param object
     * @return
     */
    public static String toJsonStringCustomerlized(ObjectMapper objectMapperCustomerlized, Object object)
    {
        try
        {
            return objectMapperCustomerlized.writeValueAsString(object);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * json string 转list
     *
     * @param json
     * @param tClass
     * @return
     */
    public static <T> List<T> parseList(String json, Class<T> tClass)
    {
        JavaType javaType = objectMapperCommon.getTypeFactory().constructParametricType(List.class, tClass);
        try
        {
            List<T> list = objectMapperCommon.readValue(json, javaType);

            return list;
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 自定义规则 json string 转list
     * @param objectMapperCustomerlized
     * @param json
     * @param tClass
     * @return
     */
    public static <T> List<T> parseListCustomerlized(ObjectMapper objectMapperCustomerlized, String json,
            Class<T> tClass)
    {
        JavaType javaType = objectMapperCustomerlized.getTypeFactory().constructParametricType(List.class, tClass);
        try
        {
            List<T> list = objectMapperCustomerlized.readValue(json, javaType);

            return list;
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * json字符串转换为map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> convertToMap(String jsonString) throws Exception
    {
        return parseObject(jsonString, Map.class);
    }

    /**
     * json字符串转换为map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> convertToMapCustomerlized(ObjectMapper objectMapperCustomerlized,
            String jsonString) throws Exception
    {
        return parseObjectCustomerlized(objectMapperCustomerlized, jsonString, Map.class);
    }

}
