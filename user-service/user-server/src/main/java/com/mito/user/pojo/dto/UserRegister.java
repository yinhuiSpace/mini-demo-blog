package com.mito.user.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegister {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 密码
     */
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*]{6,20}$",message = "密码不符合要求")
    private String password;


    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Length(min = 11,max = 11,message = "手机号格式错误")
    private String phone;

    /**
     * 性别：0=男1=女
     */
    private String gender;

    /**
     * 头像
     */
    private String avatar;
}
