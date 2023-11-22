package com.octopus.base;

/**
 * @author Administrator
 */

public enum SystemCode {


    SUCCESS(0, "成功"),


    SYSTEM_ERROR(1, "系统错误"),


    SYSTEM_WARN(2, "系统警告"),

    FEIGN_CLIENT_EXCEPTION(3, "远程调用失败"),


    PARAMETERS_MISS(4, "请求参数缺失"),

    THE_PARAMETER_CANNOT_BE_EMPTY(5, "参数不能为空！"),


    UPDATE_VERSION_CONFLICT(6, "版本冲突,请稍后重试！"),


    NO_AUTHORITY(7, "没权限！"),

    JSON_PARSE_ERROR(10001,"JSON解析异常"),
    ;


    int code;
    String message;

    SystemCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
