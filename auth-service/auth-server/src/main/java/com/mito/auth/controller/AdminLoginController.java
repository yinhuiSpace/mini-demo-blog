package com.mito.auth.controller;

import com.mito.auth.pojo.dto.UserLoginDto;
import com.mito.auth.service.ManagerLoginService;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class AdminLoginController {

    @Resource
    ManagerLoginService managerLoginService;

    @PostMapping("/login")
    public Object login(@RequestBody UserLoginDto userLoginDto){


        return RestResult.ok().setContent(managerLoginService.login(userLoginDto));
    }

}
