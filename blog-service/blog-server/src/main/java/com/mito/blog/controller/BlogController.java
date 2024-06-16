package com.mito.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mito.blog.pojo.po.Blog;
import com.mito.blog.pojo.vo.*;
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
    public Object articleList(@RequestParam(value = "categoryId",required = false) String categoryId,
                              @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){

        return RestResult.ok().setContent(blogService.articleList(Long.parseLong(categoryId),pageNum,pageSize));
    }

    @GetMapping("/details/{id}")
    public Object articleDetail(@PathVariable("id")Long id){

        return RestResult.ok().setContent(blogService.articleDetail(id));
    }

    @GetMapping("/count")
    public RestResult<Long> count(@RequestParam("id")Long id){

        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getCreateBy,id);

        return RestResult.ok(blogService.count(wrapper));
    }

    @GetMapping("/list")
    public Object list(@RequestParam(value = "pageNum",defaultValue = "1")Long pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "10") Long pageSize,
                       @RequestParam(value = "title",required = false)String title){

        return RestResult.ok().setContent(blogService.getPage(pageNum,pageSize,title));
    }

    @GetMapping("/review")
    public Object review(@RequestParam(value = "pageNum",defaultValue = "1")Long pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "10") Long pageSize,
                       @RequestParam(value = "title",required = false)String title){

        return RestResult.ok().setContent(blogService.getReview(pageNum,pageSize,title));
    }

    @DeleteMapping("/{id}")
    public Object deleteById(@PathVariable("id")String id){

        blogService.deleteById(Long.parseLong(id));

        return RestResult.ok();
    }

    @PutMapping("/update")
    public Object updateById(@RequestBody BlogUpdateVo blogUpdateVo){

        blogService.updateBlog(blogUpdateVo);

        return RestResult.ok();
    }

    @GetMapping("/one")
    public Blog getOne(@RequestParam("id")Long id){

        Blog blog = blogService.getById(id);

        return blog;
    }
}
