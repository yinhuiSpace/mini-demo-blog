package com.mito.blog.controller;

import com.mito.blog.pojo.po.Blog;
import com.mito.blog.service.BlogService;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class BlogCreateController {

    @Resource
    BlogService blogService;

    @PostMapping
    public Object article(@RequestBody Blog blog){

        blogService.createBlog(blog);

        return RestResult.ok();
    }
}
