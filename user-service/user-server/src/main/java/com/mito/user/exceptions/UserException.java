package com.mito.user.exceptions;


import com.mito.common.enums.RestResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserException extends RuntimeException{

    private Integer code;


    private String message;

    public UserException(RestResultEnum restResultEnum){

        setCode(restResultEnum.getCode());
        setMessage(restResultEnum.getMessage());
    }

}
