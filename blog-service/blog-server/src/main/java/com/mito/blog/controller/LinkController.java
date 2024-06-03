package com.mito.blog.controller;


import com.mito.blog.pojo.po.Link;
import com.mito.blog.pojo.vo.LinkVo;
import com.mito.blog.service.LinkService;
import com.mito.common.result.ListVo;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author root
 * @since 2024-06-03
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    LinkService linkService;

    @GetMapping("/getAllLinks")
    public Object getAllLinks() {

        return RestResult.ok().setContent(linkService.getAllLinks());
    }
}
