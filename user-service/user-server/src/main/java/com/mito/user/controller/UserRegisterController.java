package com.mito.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.mito.common.annotations.WebLog;
import com.mito.common.result.RestResult;
import com.mito.user.constants.RegisterConstants;
import com.mito.user.pojo.dto.RegisterMailDTO;
import com.mito.user.pojo.dto.UserRegister;
import com.mito.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserRegisterController {


    @Resource
    UserService userService;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    @WebLog(businessName = "用户注册")
    public Object register(@RequestBody UserRegister userRegister) {

        userService.register(userRegister);

        return RestResult.ok();
    }

    @PostMapping("/verifyCode")
    public Object verifyCode(@RequestBody RegisterMailDTO mailDTO) {

        String verifyCode = UUID.randomUUID().toString().substring(0, 6);

        stringRedisTemplate.opsForValue().set(RegisterConstants.REGISTER_VERIFY_CODE + mailDTO.getEmail(), verifyCode,5, TimeUnit.MINUTES);

        mailDTO.setContent("\t心流社区\n\n欢迎注册心流社区，以下是注册账号的验证码，请勿泄露：\n\n" + verifyCode + "\n\n本邮件为系统自动发送，请勿回复!\n\n");

        String exchange = "mail";
        String key = "verifyCode";
        rabbitTemplate.convertAndSend(exchange, key, BeanUtil.beanToMap(mailDTO, new HashMap<>(), false, true));

        return RestResult.ok();
    }
}
