package com.mito.user.controller;

import com.mito.common.enums.RestResultEnum;
import com.mito.common.result.RestResult;
import com.mito.common.utils.SecurityContextUtil;
import com.mito.user.exceptions.UserException;
import com.mito.user.pojo.po.User;
import com.mito.user.pojo.vo.UserInfoVo;
import com.mito.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/userInfo")
    public Object userInfo(){

        long id = getId();

        return RestResult.ok().setContent(userService.userInfo(id));
    }

    @PutMapping("/userInfo")
    public Object updateUserInfo(@RequestBody User user){

        user.setId(getId());
        userService.updateUserInfo(user);

        return RestResult.ok();
    }

    private long getId() {
        String userId = SecurityContextUtil.getUserId();
        if (!StringUtils.hasText(userId)){
            throw new UserException(RestResultEnum.NEED_LOGIN);
        }
        long id = Long.parseLong(userId);
        return id;
    }

}
