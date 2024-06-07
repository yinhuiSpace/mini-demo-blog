package com.mito.user.controller;

import com.mito.common.annotations.WebLog;
import com.mito.common.result.RestResult;
import com.mito.user.pojo.dto.UserRegister;
import com.mito.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRegisterController {


    @Resource
    UserService userService;

    @PostMapping("/register")
    @WebLog(businessName = "用户注册")
    public Object register(@RequestBody UserRegister userRegister){

        userService.register(userRegister);

        return RestResult.ok();
    }
}
