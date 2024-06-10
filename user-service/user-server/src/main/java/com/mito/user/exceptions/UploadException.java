package com.mito.user.exceptions;

import com.mito.common.enums.RestResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UploadException extends RuntimeException{

    private Integer code;


    private String message;

    public UploadException(RestResultEnum restResultEnum){

        this.code=restResultEnum.getCode();

        this.message=restResultEnum.getMessage();
    }
}
