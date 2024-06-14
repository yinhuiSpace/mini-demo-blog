package com.mito.auth.service;


import com.mito.auth.pojo.dto.UserLoginDto;
import com.mito.auth.pojo.vo.LoginVo;
import com.mito.auth.pojo.vo.UserInfoVo;

public interface UserLoginService {


    LoginVo<UserInfoVo> login(UserLoginDto userLoginDto);
}
