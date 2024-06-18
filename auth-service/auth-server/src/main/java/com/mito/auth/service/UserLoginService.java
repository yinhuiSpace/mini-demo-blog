package com.mito.auth.service;


import com.mito.auth.pojo.dto.UserLoginDto;
import com.mito.auth.pojo.vo.LoginVo;
import com.mito.auth.pojo.vo.UserInfoVo;
import jakarta.servlet.http.HttpServletResponse;

public interface UserLoginService {


    LoginVo<UserInfoVo> login(UserLoginDto userLoginDto);

    void verifyCode(HttpServletResponse response, UserLoginDto userLoginDto)throws Exception;
}
