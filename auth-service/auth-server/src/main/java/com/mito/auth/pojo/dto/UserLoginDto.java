package com.mito.auth.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserLoginDto {

    /**
     * 用户名
     */
    private String username;


    /**
     * 密码
     */
    private String password;
}
