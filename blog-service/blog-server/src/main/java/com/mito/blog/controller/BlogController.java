package com.mito.blog.controller;


import com.mito.blog.pojo.po.Blog;
import com.mito.blog.pojo.vo.BlogDetailVo;
import com.mito.blog.pojo.vo.BlogVo;
import com.mito.blog.pojo.vo.HotArticleVo;
import com.mito.blog.service.BlogService;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/article")
public class BlogController {

    @Resource
    BlogService blogService;


    @GetMapping("/hotArticleList")
    public Object hotArticleList() {

        List<HotArticleVo> blogList = blogService.hotArticleList();

        return RestResult.ok().setContent(blogList);
    }

    @GetMapping("/articleList")
    public Object articleList(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){

        return RestResult.ok().setContent(blogService.articleList(categoryId,pageNum,pageSize));
    }

    @GetMapping("/details/{id}")
    public Object articleDetail(@PathVariable("id")Long id){

        return RestResult.ok().setContent(blogService.articleDetail(id));
    }
}
