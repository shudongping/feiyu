package com.feiyu.common.core.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feiyu.common.core.domain.CodeMessage;
import com.feiyu.common.core.domain.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author shudongping
 * @date 2018/09/14
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomAccessDeniedHandler extends OAuth2AccessDeniedHandler {

    @Autowired
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException, ServletException {
        log.info("授权失败，禁止访问 {}", request.getRequestURI());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter printWriter = response.getWriter();
        Result resultBean = Result.error(CodeMessage.USER_UNAUTHORIZED);
        printWriter.append(objectMapper.writeValueAsString(resultBean));
    }


}
