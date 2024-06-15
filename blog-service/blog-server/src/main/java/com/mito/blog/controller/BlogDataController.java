package com.mito.blog.controller;

import com.mito.blog.pojo.vo.BlogDataVo;
import com.mito.blog.service.BlogService;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogDataController {


    @Resource
    BlogService blogService;

    @GetMapping("/data")
    public Object getData(){

        return RestResult.ok().setContent(blogService.getData());
    }

}
