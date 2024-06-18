package com.mito.auth.service.impl;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.json.JSONUtil;
import com.mito.auth.constants.LoginConstants;
import com.mito.auth.exception.SystemException;
import com.mito.auth.pojo.dto.UserLoginDto;
import com.mito.auth.pojo.po.LoginUserDetails;
import com.mito.auth.pojo.po.User;
import com.mito.auth.pojo.vo.LoginVo;
import com.mito.auth.pojo.vo.UserInfoVo;
import com.mito.auth.service.UserLoginService;
import com.mito.common.enums.RestResultEnum;
import com.mito.common.utils.BeanCopyUtil;
import com.mito.common.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserLoginServiceImpl implements UserLoginService {


    @Resource
    AuthenticationManager authenticationManager;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public LoginVo<UserInfoVo> login(UserLoginDto userLoginDto) {

        String verifyCode = stringRedisTemplate.opsForValue().get(LoginConstants.LOGIN_CODE + userLoginDto.getUsername());
        if (verifyCode==null){
            throw new SystemException(RestResultEnum.VERIFY_CODE_EXPIRATION);
        }
        if (!verifyCode.equalsIgnoreCase(userLoginDto.getVerifyCode())){
            throw new SystemException(RestResultEnum.VERIFY_CODE);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginDto.getUsername(),
                userLoginDto.getPassword());

        //认证
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //判定认证是否通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }

        //创建token
        LoginUserDetails loginUserDetails = (LoginUserDetails) authenticate.getPrincipal();
        User user = loginUserDetails.getUser();

        String token = JwtUtils.createToken(LoginConstants.USER_ID, user.getId().toString(), 1);
        //缓存token
        stringRedisTemplate.opsForValue().set(LoginConstants.BLOG_LOGIN + user.getId(), JSONUtil.toJsonStr(loginUserDetails));

        //封装成对象
        LoginVo<UserInfoVo> loginVo = new LoginVo<>();

        loginVo.setInfoVo(BeanCopyUtil.copyBean(user, UserInfoVo.class));
        loginVo.setToken(token);

        return loginVo;
    }

    @Override
    public void verifyCode(HttpServletResponse response, UserLoginDto userLoginDto) throws Exception {

        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(50, 25, 4, 20);

        //1.缓存验证码
        stringRedisTemplate.opsForValue().set(
                LoginConstants.LOGIN_CODE + userLoginDto.getUsername(),
                captcha.getCode(),
                60,
                TimeUnit.SECONDS
        );

        //2.将图像写出
        try (
                ServletOutputStream outputStream = response.getOutputStream()
        ) {
            captcha.write(outputStream);
        }

    }
}
