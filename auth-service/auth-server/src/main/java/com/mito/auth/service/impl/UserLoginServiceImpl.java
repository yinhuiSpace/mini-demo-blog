package com.mito.auth.service.impl;


import cn.hutool.json.JSONUtil;
import com.mito.auth.pojo.dto.UserLoginDto;
import com.mito.auth.pojo.po.LoginUserDetails;
import com.mito.auth.pojo.po.User;
import com.mito.auth.pojo.vo.LoginVo;
import com.mito.auth.pojo.vo.UserInfoVo;
import com.mito.auth.service.UserLoginService;
import com.mito.common.utils.BeanCopyUtil;
import com.mito.common.utils.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserLoginServiceImpl implements UserLoginService {


    @Resource
    AuthenticationManager authenticationManager;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public LoginVo<UserInfoVo> login(UserLoginDto userLoginDto) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginDto.getUsername(),
                userLoginDto.getPassword());

        //认证
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //判定认证是否通过
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }

        //创建token
        LoginUserDetails loginUserDetails = (LoginUserDetails) authenticate.getPrincipal();
        User user = loginUserDetails.getUser();

        String token = JwtUtils.createToken("userId",user.getId().toString(), 1);
        //缓存token
        stringRedisTemplate.opsForValue().set("bloglogin:"+user.getId(),JSONUtil.toJsonStr(loginUserDetails));

        //封装成对象
        LoginVo<UserInfoVo> loginVo = new LoginVo<>();

        return loginVo.setToken(token)
                .setInfoVo(BeanCopyUtil.copyBean(user, UserInfoVo.class));
    }
}
