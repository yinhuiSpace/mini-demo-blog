package com.mito.user.controller;

import com.mito.client.BlogClient;
import com.mito.common.result.RestResult;
import com.mito.common.utils.BeanCopyUtil;
import com.mito.user.pojo.po.User;
import com.mito.user.pojo.vo.PersonInfoVo;
import com.mito.user.service.FansService;
import com.mito.user.service.FollowService;
import com.mito.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class PersonInfoController {

    @Resource
    UserService userService;

    @Resource
    FansService fansService;

    @Resource
    FollowService followService;

    @Resource
    BlogClient blogClient;

    @GetMapping
    public Object info(@RequestParam("id")String id){

        Long userId = Long.parseLong(id);

        User user = userService.getById(userId);

        PersonInfoVo personInfoVo = BeanCopyUtil.copyBean(user, PersonInfoVo.class);
        personInfoVo.setFans(fansService.fansCount(userId))
                .setFollowers(followService.followedCount(userId))
                .setArticles(blogClient.count(userId).getContent());


        return RestResult.ok().setContent(personInfoVo);
    }

}
