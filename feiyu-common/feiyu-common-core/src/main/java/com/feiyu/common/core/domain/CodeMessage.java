package com.feiyu.common.core.domain;

import lombok.Data;

@Data
public class CodeMessage {

    private int code;

    private String msg;

    public static CodeMessage USER_UNAUTHORIZED = new CodeMessage(401, "没有相关权限！");

    public static CodeMessage USER_UNAUTHENTICATED = new CodeMessage(403, "没有认证！");
    public CodeMessage() {
    }

    public CodeMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
