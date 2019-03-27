package com.feiyu.auth.security.component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Author shudongping
 * @Date 2019/3/27
 */
@JsonSerialize(using = FeiyuAuth2ExceptionSerializer.class)
public class FeiyuAuth2Exception extends OAuth2Exception {

    @Getter
    private int code;

    public FeiyuAuth2Exception(String msg) {
        super(msg);
    }

    public FeiyuAuth2Exception(String msg, int code) {
        super(msg);
        this.code = code;
    }

}
