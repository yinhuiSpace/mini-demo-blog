package com.mito.auth.service.impl;

import cn.hutool.json.JSONUtil;
import com.mito.auth.exception.SystemException;
import com.mito.auth.pojo.po.LoginUserDetails;
import com.mito.auth.service.UserLogoutService;
import com.mito.common.enums.RestResultEnum;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserLogoutServiceImpl implements UserLogoutService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void logout(String userId) {
        //从redis中获取用户信息
        String loginUserDetailsJson = stringRedisTemplate.opsForValue().get("bloglogin:" + userId);
        if (!StringUtils.hasText(loginUserDetailsJson)){
            throw new SystemException(RestResultEnum.NEED_LOGIN);
        }
//
//        LoginUserDetails loginUserDetails = JSONUtil.toBean(loginUserDetailsJson, LoginUserDetails.class);
        stringRedisTemplate.delete("bloglogin:"+userId);
    }
}
