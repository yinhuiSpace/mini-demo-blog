package com.mito.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mito.blog.constants.BlogConstants;
import com.mito.blog.pojo.po.Blog;
import com.mito.blog.mapper.BlogMapper;
import com.mito.blog.pojo.po.Category;
import com.mito.blog.pojo.vo.BlogDetailVo;
import com.mito.blog.pojo.vo.BlogListVo;
import com.mito.blog.pojo.vo.BlogVo;
import com.mito.blog.pojo.vo.HotArticleVo;
import com.mito.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mito.blog.service.CategoryService;
import com.mito.common.utils.BeanCopyUtil;
import com.mito.common.utils.DateTimeClient;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Resource
    @Lazy
    CategoryService categoryService;

    @Override
    public List<HotArticleVo> hotArticleList() {

        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();

        //必须是正式文章
        wrapper.eq(Blog::getStatus, BlogConstants.ARTICLE_STATUS_NORMAL);

        //按照浏览量降序排列
        wrapper.orderByDesc(Blog::getViewCount);

        //只查10条
        Page<Blog> page = new Page<>(1, BlogConstants.HOT_ARTICLE_RANK_SIZE);

        page(page, wrapper);
        List<HotArticleVo> voList = page.getRecords().stream().map(new Function<Blog, HotArticleVo>() {
            @Override
            public HotArticleVo apply(Blog blog) {

                return BeanCopyUtil.copyBean(blog, HotArticleVo.class);
            }
        }).collect(Collectors.toList());

        return voList;
    }

    @Override
    public BlogListVo articleList(Integer categoryId, Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        //正常文章，属于该分类，置顶在前
        wrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Blog::getCategoryId, categoryId)
                .eq(Blog::getStatus, BlogConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Blog::getIsTop);

        Page<Blog> page = new Page<>(pageNum, pageSize);
        Page<Blog> blogPage = page(page, wrapper);

        List<BlogVo> blogVoList = blogPage.getRecords().stream().map(new Function<Blog, BlogVo>() {
            @Override
            public BlogVo apply(Blog blog) {

                BlogVo blogVo = BeanCopyUtil.copyBean(blog, BlogVo.class);

                blogVo.setCategoryName(categoryService.getById(blog.getCategoryId()).getName())
                        .setCreateTimeStr(DateTimeClient.toStr(blog.getCreateTime(), DateTimeClient.SIMPLE_FORMAT));

                return blogVo;
            }
        }).collect(Collectors.toList());

        BlogListVo blogListVo = new BlogListVo();
        blogListVo.setRows(blogVoList)
                .setTotal(blogPage.getTotal());

        return blogListVo;
    }

    @Override
    public BlogDetailVo articleDetail(Long id) {

        //
        Blog blog = getById(id);
        BlogDetailVo blogDetailVo = BeanCopyUtil.copyBean(blog, BlogDetailVo.class);

        Category category = categoryService.getById(blog.getCategoryId());
        if (category==null){
            throw new RuntimeException("分类信息错误");
        }

        blogDetailVo.setCreateTimeStr(DateTimeClient.toStr(blog.getCreateTime(),DateTimeClient.SIMPLE_FORMAT))
                .setCategoryName(category.getName());

        return blogDetailVo;
    }
}
