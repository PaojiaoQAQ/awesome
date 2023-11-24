package com.example.demo.common.entity;

import com.example.demo.common.constant.ResultCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description 通用返回格式
 * @author tanyue
 * @Date 2021/4/10
 **/
@Data
public class Result<T>
{
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回code")
    private Integer resultcCode;
    @ApiModelProperty(value = "返回信息")
    private String resultMessage;
    @ApiModelProperty(value = "返回数据")
    private T data;

    public Result success(T data){
        Result result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setResultcCode(ResultCodeEnum.SUCCESS.getCode());
        result.setResultMessage(ResultCodeEnum.SUCCESS.getDesc());
        result.setData(data);
        return result;
    }

    public static Result fail(ResultCodeEnum err){
        Result result = new Result<>();
        result.setSuccess(Boolean.FALSE);
        result.setResultcCode(err.getCode());
        result.setResultMessage(err.getDesc());
        return result;
    }

    public Result success(){
        Result result = new Result<>();
        result.setSuccess(Boolean.TRUE);
        result.setResultcCode(ResultCodeEnum.SUCCESS.getCode());
        result.setResultMessage(ResultCodeEnum.SUCCESS.getDesc());
        return result;
    }

}