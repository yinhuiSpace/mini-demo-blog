package com.mito.auth.controller;

import com.mito.auth.exception.SystemException;
import com.mito.auth.service.UserLogoutService;
import com.mito.common.enums.RestResultEnum;
import com.mito.common.result.RestResult;
import com.mito.common.utils.SecurityContextUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserLogoutController {

    @Resource
    UserLogoutService userLogoutService;


    @PostMapping("/logout")
    public Object logout(){

        String userId = SecurityContextUtil.getUserId();
        if (!StringUtils.hasText(userId)){
            throw new SystemException(RestResultEnum.NEED_LOGIN);
        }
        userLogoutService.logout(userId);

        return RestResult.ok();
    }
}
