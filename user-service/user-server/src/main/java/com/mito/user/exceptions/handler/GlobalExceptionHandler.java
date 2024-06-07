package com.mito.user.exceptions.handler;

import com.mito.common.enums.RestResultEnum;
import com.mito.common.result.RestResult;
import com.mito.user.exceptions.UserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object doOnException(Exception e){

        e.printStackTrace();
        return RestResult.fail(RestResultEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(UserException.class)
    public Object doOnUserException(UserException e){

        e.printStackTrace();
        return RestResult.fail(e.getMessage(),e.getCode());
    }
}
