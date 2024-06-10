package com.mito.user.exceptions;


import com.mito.common.enums.RestResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FollowException extends RuntimeException{

    private Integer code;


    private String message;

    public FollowException(RestResultEnum restResultEnum){

        this.code=restResultEnum.getCode();

        this.message=restResultEnum.getMessage();
    }
}
