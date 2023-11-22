package com.octopus.base.exception;

/**
 * 自定义异常
 **/
public class BizException extends RuntimeException {

    public BizException(String message){
        super(message);
    }

    public BizException(String message, Throwable cause){
        super(message, cause);
    }

    public BizException(Throwable cause){
        super(cause);
    }

    public static BizException from(String errorMsg) {
        return new BizException(errorMsg);
    }
}