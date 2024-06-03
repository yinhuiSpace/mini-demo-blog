package com.mito.blog.controller;


import com.mito.blog.pojo.vo.CategoryVo;
import com.mito.blog.service.CategoryService;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
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
    public Object getCateGoryList(){

        List<CategoryVo> categoryVos=categoryService.getCategoryList();

        return RestResult.ok().setContent(categoryVos);
    }

}
