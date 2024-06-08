package com.mito.blog.controller;

import com.mito.blog.service.ViewCountIncrService;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class BlogIncrController {

    @Resource
    ViewCountIncrService viewCountIncrService;

    @PutMapping("/updateViewCount/{id}")
    public Object updateViewCount(@PathVariable("id")String id){

        viewCountIncrService.incr(id);

        return RestResult.ok();
    }

}
