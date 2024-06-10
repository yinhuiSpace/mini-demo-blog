package com.mito.user.controller;


import com.mito.common.result.RestResult;
import com.mito.common.utils.SecurityContextUtil;
import com.mito.user.service.FollowService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2024-06-10
 */
@RestController
@RequestMapping("/follow")
public class FollowController {

    @Resource
    FollowService followService;

    @GetMapping("/count")
    public Object count(){
        Long userId = Long.parseLong(SecurityContextUtil.getUserId());

        return RestResult.ok().setContent(followService.followedCount(userId));
    }

}
