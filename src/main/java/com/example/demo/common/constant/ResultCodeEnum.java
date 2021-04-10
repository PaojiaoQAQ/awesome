package com.example.demo.common.constant;

/**
 * @author tanyue
 *
 * @Date 2021/4/10
 **/
public enum ResultCodeEnum
{
    SUCCESS(10001,"成功"),
    FAIL_UNKNOW(99999,"未知错误");

    private Integer code;
    private String desc;

    ResultCodeEnum(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }
    public Integer getCode()
    {
        return code;
    }
    public String getDesc()
    {
        return desc;
    }
}
