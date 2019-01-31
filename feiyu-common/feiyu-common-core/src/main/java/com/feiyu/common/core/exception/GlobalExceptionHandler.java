package com.feiyu.common.core.exception;


import com.feiyu.common.core.domain.CodeMessage;
import com.feiyu.common.core.domain.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Object> handleBadRequest(Exception e) {
        e.printStackTrace();
        return Result.error(new CodeMessage(500, "服务器错误"));
    }

    @ExceptionHandler(value = ServiceException.class)
    public Result<Object> handleServiceException(Exception e) {
        ServiceException serviceException = (ServiceException) e;
        return Result.error(serviceException.getCm());
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<Object> AccessDeniedException(Exception e) {
        return Result.error(CodeMessage.USER_UNAUTHORIZED);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result<Object> handleAuthenticationException(Exception e) {
        return Result.error(CodeMessage.USER_UNAUTHORIZED);
    }
}
