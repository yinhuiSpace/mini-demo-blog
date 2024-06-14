package com.mito.auth.service.impl;

import cn.hutool.json.JSONUtil;
import com.mito.auth.pojo.dto.UserLoginDto;
import com.mito.auth.pojo.po.LoginUserDetails;
import com.mito.auth.pojo.po.User;
import com.mito.auth.pojo.vo.LoginVo;
import com.mito.auth.pojo.vo.ManagerInfoVo;
import com.mito.auth.service.ManagerLoginService;
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
public class ManagerLoginServiceImpl implements ManagerLoginService {

    @Resource
    AuthenticationManager authenticationManager;

    @Resource
    StringRedisTemplate stringRedisTemplate;


    @Override
    public LoginVo<ManagerInfoVo> login(UserLoginDto userLoginDto) {

        String account = userLoginDto.getUsername();
        String credential = userLoginDto.getPassword();

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(account, credential);

        Authentication result = authenticationManager.authenticate(token);

        if (Objects.isNull(result)){
            throw new RuntimeException("账号或者密码错误");
        }
        LoginUserDetails userDetails = (LoginUserDetails) result.getPrincipal();
        User user = userDetails.getUser();

        if (!"1".equals(user.getType())){
            throw new RuntimeException("非法用户无法使用本系统");
        }

        stringRedisTemplate.opsForValue().set("login:"+user.getId(), JSONUtil.toJsonStr(user));

        String jwt = JwtUtils.createToken("userId", user.getId(), 1);

        LoginVo<ManagerInfoVo> managerInfoVoLoginVo = new LoginVo<>();

        managerInfoVoLoginVo.setToken(jwt)
                .setInfoVo(BeanCopyUtil.copyBean(user, ManagerInfoVo.class));

        return managerInfoVoLoginVo;
    }
}
