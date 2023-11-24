package com.example.demo.common.utils.future;

import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

/**
 * @description
 * @author tanyue
 * @Date 2022/7/14
 **/
public class ExceptionUtils
{
    /**
     * 提取真正的异常
     */
    public static Throwable extractRealException(Throwable throwable) {
        if (throwable instanceof CompletionException || throwable instanceof ExecutionException) {
            if (throwable.getCause() != null) {
                return throwable.getCause();
            }
        }
        return throwable;
    }
}
