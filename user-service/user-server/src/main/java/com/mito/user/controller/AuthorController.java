package com.mito.user.controller;

import com.mito.common.result.RestResult;
import com.mito.user.pojo.vo.AuthorInfoVo;
import com.mito.user.service.AuthorService;
import com.mito.user.service.FansService;
import com.mito.user.service.FollowService;
import com.mito.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Resource
    AuthorService authorService;

    @GetMapping
    public Object author(@RequestParam("id") String id){

        return RestResult.ok().setContent(authorService.author(Long.parseLong(id)));
    }

}
