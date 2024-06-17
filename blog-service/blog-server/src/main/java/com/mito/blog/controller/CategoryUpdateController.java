package com.mito.blog.controller;

import com.mito.blog.pojo.dto.CategoryDTO;
import com.mito.blog.service.CategoryService;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryUpdateController {

    @Resource
    CategoryService categoryService;

    @PostMapping("/add")
    public Object add(@RequestBody CategoryDTO categoryDTO){

        categoryService.add(categoryDTO);

        return RestResult.ok();
    }

    @DeleteMapping("/{id}")
    public Object deleteById(@PathVariable("id")String id){

        categoryService.deleteById(Long.parseLong(id));

        return RestResult.ok();
    }

    @PutMapping("/update")
    public Object updateById(@RequestBody CategoryDTO categoryDTO){

        categoryService.updateCategory(categoryDTO);

        return RestResult.ok();
    }

}
