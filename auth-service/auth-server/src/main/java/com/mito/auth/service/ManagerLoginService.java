package com.mito.auth.service;

import com.mito.auth.pojo.dto.UserLoginDto;
import com.mito.auth.pojo.vo.LoginVo;
import com.mito.auth.pojo.vo.ManagerInfoVo;

public interface ManagerLoginService {


    LoginVo<ManagerInfoVo> login(UserLoginDto userLoginDto);
}
