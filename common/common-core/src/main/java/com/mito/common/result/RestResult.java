package com.mito.common.result;

import com.mito.common.enums.RestResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class RestResult<T> {

    /**
     * 业务是否成功
     * */
    Boolean isSuccess;

    /**
     * 状态码，主要是否方便前端分类处理
     * */
    Integer code;

    String message;

    T content;

    public static <T> RestResult<T> ok(){



        return RestResult
                .<T>builder()
                .code(RestResultEnum.SUCCESS.getCode())
                .isSuccess(true)
                .message(RestResultEnum.SUCCESS.getMessage())
                .build();
    }

    public static <T> RestResult<T> ok(T content){

        return RestResult
                .<T>builder()
                .code(RestResultEnum.SUCCESS.getCode())
                .content(content)
                .isSuccess(true)
                .message(RestResultEnum.SUCCESS.getMessage())
                .build();
    }

    public static <T> RestResult<T> fail(RestResultEnum restResultEnum){

        return RestResult
                .<T>builder()
                .code(restResultEnum.getCode())
                .isSuccess(false)
                .message(restResultEnum.getMessage())
                .content(null)
                .build();
    }

    public static <T> RestResult<T> fail(String message,Integer code){

        return RestResult
                .<T>builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .build();
    }

}
