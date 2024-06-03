package com.mito.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mito.blog.constants.CategoryConstants;
import com.mito.blog.pojo.po.Blog;
import com.mito.blog.pojo.po.Category;
import com.mito.blog.mapper.CategoryMapper;
import com.mito.blog.pojo.vo.CategoryVo;
import com.mito.blog.service.BlogService;
import com.mito.blog.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mito.common.utils.BeanCopyUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author root
 * @since 2024-06-03
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    @Lazy
    BlogService blogService;


    @Override
    public List<CategoryVo> getCategoryList() {

        //1.查出文章表中的所有分类
        List<Blog> blogs = blogService.list();
        Set<Long> categoryIds = blogs.stream().map(new Function<Blog, Long>() {
            @Override
            public Long apply(Blog blog) {

                return blog.getCategoryId();
            }
        }).collect(Collectors.toSet());

        //2.根据id集合在分类表中查出具体数据
        List<Category> categories = listByIds(categoryIds);
        List<CategoryVo> categoryVoList = categories.stream().filter(new Predicate<Category>() {
            @Override
            public boolean test(Category category) {
                return category.getStatus().equals(CategoryConstants.CATEGORY_NORMAL);
            }
        }).map(new Function<Category, CategoryVo>() {
            @Override
            public CategoryVo apply(Category category) {

                return BeanCopyUtil.copyBean(category, CategoryVo.class);
            }
        }).collect(Collectors.toList());

        return categoryVoList;
    }
}
