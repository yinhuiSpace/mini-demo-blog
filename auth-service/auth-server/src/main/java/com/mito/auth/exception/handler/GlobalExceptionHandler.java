package com.mito.auth.exception.handler;

import com.mito.auth.exception.SystemException;
import com.mito.common.enums.RestResultEnum;
import com.mito.common.result.RestResult;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object doOnException(Exception e){

        e.printStackTrace();
        return RestResult.fail(RestResultEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(SystemException.class)
    public Object doOnSystemException(SystemException e){

        return RestResult.fail(e.getMessage(),e.getCode());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Object doOnBadCredentialsException(BadCredentialsException e){
        e.printStackTrace();

        return RestResult.fail(e.getMessage(),401);
    }



}
