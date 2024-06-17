package com.mito.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mito.blog.constants.CategoryConstants;
import com.mito.blog.pojo.dto.CategoryDTO;
import com.mito.blog.pojo.po.Blog;
import com.mito.blog.pojo.po.Category;
import com.mito.blog.mapper.CategoryMapper;
import com.mito.blog.pojo.vo.CategoryListVo;
import com.mito.blog.pojo.vo.CategoryQueryVo;
import com.mito.blog.pojo.vo.CategoryVo;
import com.mito.blog.service.BlogService;
import com.mito.blog.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mito.common.utils.BeanCopyUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public List<CategoryVo> getFirst() {

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getParentId, 0);

        List<Category> categories = list(wrapper);

        return categories.stream().map(new Function<Category, CategoryVo>() {
            @Override
            public CategoryVo apply(Category category) {
                return BeanCopyUtil.copyBean(category, CategoryVo.class);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryVo> getSecond(Long parentId) {

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getParentId, parentId);

        return list(wrapper).stream().map(new Function<Category, CategoryVo>() {
            @Override
            public CategoryVo apply(Category category) {
                return BeanCopyUtil.copyBean(category, CategoryVo.class);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryVo> getCategories() {

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(Category::getParentId, 0);

        return list(wrapper).stream().map(new Function<Category, CategoryVo>() {
            @Override
            public CategoryVo apply(Category category) {
                return BeanCopyUtil.copyBean(category, CategoryVo.class);
            }
        }).collect(Collectors.toList());

    }

    @Override
    public void add(CategoryDTO categoryDTO) {

        Category category = BeanCopyUtil.copyBean(categoryDTO, Category.class);
        category.setId(IdUtil.getSnowflake().nextId())
                .setParentId(Long.parseLong(categoryDTO.getParentId()));

        save(category);

    }

    @SuppressWarnings("SpringTransactionalMethodCallsInspection")
    @Override
    public void deleteById(Long id) {

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getParentId,id);
        Set<Long> ids = list(queryWrapper).stream().map(new Function<Category, Long>() {
            @Override
            public Long apply(Category category) {
                return category.getId();
            }
        }).collect(Collectors.toSet());

        removeById(id);

        if (ids.size()>0){
            removeBatchByIds(ids);
        }

    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {

        Category category = BeanCopyUtil.copyBean(categoryDTO, Category.class);
        category.setId(Long.parseLong(categoryDTO.getId()))
                .setParentId(Long.parseLong(categoryDTO.getParentId()));

        updateById(category);
    }

    @Override
    public CategoryListVo getCategoryPage(Long pageNum, Long pageSize, String name) {

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(name),Category::getName,name)
                .eq(Category::getStatus,CategoryConstants.CATEGORY_NORMAL);

        Page<Category> page = page(Page.of(pageNum, pageSize), wrapper);
        List<CategoryQueryVo> categoryVoList = page.getRecords().stream().map(new Function<Category, CategoryQueryVo>() {
            @Override
            public CategoryQueryVo apply(Category category) {
                return BeanCopyUtil.copyBean(category, CategoryQueryVo.class);
            }
        }).collect(Collectors.toList());

        CategoryListVo categoryListVo = new CategoryListVo();

        categoryListVo.setTotal(page.getTotal())
                .setRows(categoryVoList);

        return categoryListVo;
    }
}
