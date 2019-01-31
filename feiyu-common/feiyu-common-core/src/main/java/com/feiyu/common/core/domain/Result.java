package com.feiyu.common.core.domain;

import lombok.Data;

/**
 * restful对外的JSON 对象封装
 *
 * @author Administrator
 */
@Data
public class Result<T> {

    private String message;

    private int code = 200;

    private T data;

    private Result(T data) {
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<T>(data, message);
    }

    /**
     * 失败时候的调用
     */
    public static <T> Result<T> error(CodeMessage codeMsg) {
        return new Result<T>(codeMsg);
    }

    private Result(CodeMessage codeMsg) {
        if (codeMsg != null) {
            this.code = codeMsg.getCode();
            this.message = codeMsg.getMsg();
        }
    }

    private Result(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public static void main(String args[]) {
    }
}
