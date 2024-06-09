package com.mito.blog.exception.handler;

import com.mito.blog.exception.SystemException;
import com.mito.common.enums.RestResultEnum;
import com.mito.common.result.RestResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

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

    @ExceptionHandler(SQLException.class)
    public Object doOnSqlException(SQLException e){

        e.printStackTrace();

        return RestResult.fail(RestResultEnum.INVALID_PARAM);
    }
}
