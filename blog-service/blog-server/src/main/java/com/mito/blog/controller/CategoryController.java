package com.mito.blog.controller;


import com.mito.blog.pojo.vo.CategoryListVo;
import com.mito.blog.pojo.vo.CategoryVo;
import com.mito.blog.service.CategoryService;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/category")
public class CategoryController {

    @Resource
    CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public Object getCateGoryList() {

        List<CategoryVo> categoryVos = categoryService.getCategoryList();

        return RestResult.ok().setContent(categoryVos);
    }

    @GetMapping("/page")
    public Object page(@RequestParam(value = "pageNum",defaultValue = "1")Long pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "10")Long pageSize,
                       @RequestParam(value = "name",required = false)String name){

        return RestResult.ok().setContent(categoryService.getCategoryPage(pageNum,pageSize,name));
    }

    @GetMapping("/getFirst")
    public Object getFirst() {

        return RestResult.ok().setContent(categoryService.getFirst());
    }

    @GetMapping("/getSecond")
    public Object getSecond(@RequestParam("parentId") String parentId) {

        return RestResult.ok().setContent(categoryService.getSecond(Long.parseLong(parentId)));
    }

    @GetMapping
    public Object getCategory() {

        return RestResult.ok().setContent(categoryService.getCategories());
    }

}
