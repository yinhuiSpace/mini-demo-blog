package com.mito.auth.controller;



import com.mito.auth.exception.SystemException;
import com.mito.auth.pojo.dto.UserLoginDto;
import com.mito.auth.service.UserLoginService;
import com.mito.common.enums.RestResultEnum;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2024-06-04
 */
@RestController
@RequestMapping("/auth")
public class UserLoginController {


    @Resource
    UserLoginService userLoginService;

    @PostMapping("/login")
    public Object login(@RequestBody UserLoginDto userLoginDto){

        String username = userLoginDto.getUsername();
        if (!StringUtils.hasText(username)){
            throw new SystemException(RestResultEnum.LOGIN_ERROR);
        }

        String password = userLoginDto.getPassword();
        if (!StringUtils.hasText(password)){
            throw new SystemException(RestResultEnum.LOGIN_ERROR);
        }
        return RestResult.ok().setContent(userLoginService.login(userLoginDto));
    }

}
