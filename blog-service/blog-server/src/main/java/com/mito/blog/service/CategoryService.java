package com.mito.blog.service;

import com.mito.blog.pojo.po.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mito.blog.pojo.vo.CategoryVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2024-06-03
 */
public interface CategoryService extends IService<Category> {

    List<CategoryVo> getCategoryList();

    List<CategoryVo> getFirst();

    List<CategoryVo> getSecond(Long parentId);

    List<CategoryVo> getCategories();
}
