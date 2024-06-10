package com.mito.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mito.common.utils.BeanCopyUtil;
import com.mito.common.utils.SecurityContextUtil;
import com.mito.user.pojo.po.Fans;
import com.mito.user.pojo.po.User;
import com.mito.user.pojo.vo.AuthorInfoVo;
import com.mito.user.service.AuthorService;
import com.mito.user.service.FansService;
import com.mito.user.service.FollowService;
import com.mito.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Resource
    UserService userService;

    @Resource
    FansService fansService;

    @Resource
    FollowService followService;

    @Override
    public AuthorInfoVo author(Long id) {

        User user = userService.getById(id);
        AuthorInfoVo authorInfoVo = BeanCopyUtil.copyBean(user, AuthorInfoVo.class);

        if (SecurityContextUtil.getUserId()!=null){
            authorInfoVo.setFollow(followService.isFollow(Long.parseLong(SecurityContextUtil.getUserId()),id));
        }else {
            authorInfoVo.setFollow(false);
        }


        authorInfoVo
                .setFans(fansService.fansCount(id));

        return authorInfoVo;
    }
}
