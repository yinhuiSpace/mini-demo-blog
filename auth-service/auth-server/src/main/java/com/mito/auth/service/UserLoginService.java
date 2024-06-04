package com.mito.auth.service;


import com.mito.auth.pojo.dto.UserLoginDto;
import com.mito.auth.pojo.vo.LoginVo;

public interface UserLoginService {


    LoginVo login(UserLoginDto userLoginDto);
}
