package com.mito.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mito.auth.mapper.UserMapper;
import com.mito.auth.pojo.po.LoginUserDetails;
import com.mito.auth.pojo.po.User;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 用于从数据源加载用户信息
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //从数据库中，根据用户名查询用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);

        User user = userMapper.selectOne(wrapper);

        if (Objects.isNull(user)) {
            throw new RuntimeException("该用户不存在");
        }

        //补充完整后返回
        LoginUserDetails loginUserDetails = new LoginUserDetails();

        return loginUserDetails.setUser(user);
    }
}
