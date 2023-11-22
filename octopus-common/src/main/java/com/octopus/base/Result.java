package com.octopus.base;


import java.io.Serializable;

/**
 * 响应结果
 *
 * @author Administrator*/
public class Result<T> implements Serializable {

    private T data;
    private Integer code;
    private String message;

    public static <T> Result<T> failed() {
        return result(SystemCode.SYSTEM_ERROR.code,SystemCode.SYSTEM_ERROR.message, null);
    }


    public static <T> Result<T> failedWithMessage(SystemCode systemCode, T data) {
        return result(systemCode.getCode(), systemCode.getMessage(), data);
    }
    public static <T> Result<T> failed(SystemCode systemCode) {
        return result(systemCode.getCode(), systemCode.getMessage(), null);
    }

    public static <T> Result<T> failed(String msg) {
        int code = SystemCode.SYSTEM_ERROR.code;
        return result(code, msg, null);
    }

    public static <T> Result<T> failed(String msg, T data) {
        return result(SystemCode.SYSTEM_ERROR.code, msg, data);
    }

    public static <T> Result<T> failed(Integer code, String msg) {
        return result(code, msg, null);
    }

    public static <T> Result<T> failed(Integer code, String msg, T data) {
        return result(code, msg, data);
    }

    public static <T> Result<T> judge(boolean status) {
        if (status) {
            return success();
        } else {
            return failed();
        }
    }

    private static <T> Result<T> result(Integer code, String msg, T data) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setData(data);
        result.setMessage(msg);
        return result;
    }

    public static <T> Result<T> successWithData(T data) {
        Result<T> result = new Result();
        result.setCode(SystemCode.SUCCESS.getCode());
        result.setMessage(SystemCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }
    public static <T> Result<T> success() {
        Result<T> result = new Result();
        result.setCode(SystemCode.SUCCESS.getCode());
        result.setMessage(SystemCode.SUCCESS.getMessage());
        result.setData(null);
        return result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
