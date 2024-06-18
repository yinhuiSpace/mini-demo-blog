package com.mito.auth.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserLoginDto {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;


    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
    * 验证码
    * */
    @NotBlank(message = "验证码不能为空")
    @Length(
            min = 4,
            max = 4,
            message = "验证码长度错误"
    )
    private String verifyCode;
}

