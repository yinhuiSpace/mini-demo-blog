package com.mito.user.controller;

import com.mito.common.result.RestResult;
import com.mito.common.utils.SecurityContextUtil;
import com.mito.user.service.FansService;
import com.mito.user.service.FollowService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserFollowUpdateController {

    @Resource
    FansService fansService;

    @Resource
    FollowService followService;

    @PostMapping("/addFollow/{followId}")
    public Object addFollow(@PathVariable("followId") String followId){

        Long userId = Long.parseLong(SecurityContextUtil.getUserId());
        fansService.addFollow(userId,Long.parseLong(followId));
        followService.addFollow(userId,Long.parseLong(followId));
        return RestResult.ok().setContent(fansService.fansCount(Long.parseLong(followId)));
    }

    @DeleteMapping("/unFollow/{followId}")
    public Object upFollow(@PathVariable("followId")String followId){
        Long userId = Long.parseLong(SecurityContextUtil.getUserId());
        fansService.unFollow(userId,Long.parseLong(followId));
        followService.unFollow(userId,Long.parseLong(followId));
        return RestResult.ok().setContent(fansService.fansCount(Long.parseLong(followId)));
    }

}
