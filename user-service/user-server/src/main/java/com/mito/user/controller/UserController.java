package com.mito.user.controller;

import com.mito.user.pojo.po.User;
import com.mito.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/one")
    public User getOneById(@RequestParam("id") Long id){

        User user = userService.getById(id);

        return user;
    }

}
