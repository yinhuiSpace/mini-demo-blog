package com.mito.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mito.blog.constants.BlogConstants;
import com.mito.blog.pojo.po.Blog;
import com.mito.blog.mapper.BlogMapper;
import com.mito.blog.pojo.po.Category;
import com.mito.blog.pojo.vo.*;
import com.mito.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mito.blog.service.CategoryService;
import com.mito.client.UserClient;
import com.mito.client.pojo.po.User;
import com.mito.common.utils.BeanCopyUtil;
import com.mito.common.utils.DateTimeClient;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    UserClient userClient;

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

                HotArticleVo hotArticleVo = BeanCopyUtil.copyBean(blog, HotArticleVo.class);
                hotArticleVo.setViewCount(getViewCount(blog.getId().toString()));

                return hotArticleVo;
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

                User user = userClient.getOneById(blog.getCreateBy());

                blogVo.setCategoryName(categoryService.getById(blog.getCategoryId()).getName())
                        .setViewCount(getViewCount(blog.getId().toString()))
                        .setAuthorName(user.getNickname());

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

        blogDetailVo
                .setCategoryName(category.getName())
                .setViewCount(getViewCount(blog.getId().toString()));

        return blogDetailVo;
    }

    @Override
    public void createBlog(Blog blog) {

        Long id = IdUtil.getSnowflake().nextId();
        stringRedisTemplate.opsForHash().put("blog:viewCount",id.toString(),"0");
        blog.setId(id);

        save(blog);
    }

    @Override
    public BlogListVo getPage(Long pageNum, Long pageSize, String title) {

        Page<Blog> page = getBlogPage(pageNum, pageSize, title);

        List<BlogVo> blogVoList = page.getRecords().stream().map(new Function<Blog, BlogVo>() {
            @Override
            public BlogVo apply(Blog blog) {
                return BeanCopyUtil.copyBean(blog, BlogVo.class);
            }
        }).collect(Collectors.toList());

        BlogListVo blogListVo = new BlogListVo();
        blogListVo.setTotal(page.getTotal())
                .setRows(blogVoList);

        return blogListVo;
    }

    private Page<Blog> getBlogPage(Long pageNum, Long pageSize, String title) {
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(title),Blog::getTitle, title);

        return page(Page.of(pageNum, pageSize), wrapper);
    }

    @Override
    public BlogReviewListVo getReview(Long pageNum, Long pageSize, String title) {

        Page<Blog> page = getBlogPage(pageNum, pageSize, title);

        List<BlogReviewVo> blogReviewVoList = page.getRecords().stream().map(new Function<Blog, BlogReviewVo>() {
            @Override
            public BlogReviewVo apply(Blog blog) {
                return BeanCopyUtil.copyBean(blog, BlogReviewVo.class);
            }
        }).collect(Collectors.toList());

        BlogReviewListVo blogReviewListVo = new BlogReviewListVo();
        blogReviewListVo.setTotal(page.getTotal())
                .setRows(blogReviewVoList);


        return blogReviewListVo;
    }


    @Override
    public void deleteById(Long id) {

        removeById(id);
    }

    @Override
    public void updateBlog(BlogUpdateVo blogUpdateVo) {

        Blog blog = BeanCopyUtil.copyBean(blogUpdateVo, Blog.class);
        blog.setId(Long.parseLong(blogUpdateVo.getId()));

        updateById(blog);

    }

    @Override
    public List<BlogDataVo> getData() {

        List<Blog> blogs = list();

        return blogs.stream().map(new Function<Blog, BlogDataVo>() {
            @Override
            public BlogDataVo apply(Blog blog) {

                return BeanCopyUtil.copyBean(blog, BlogDataVo.class);
            }
        }).collect(Collectors.toList());
    }

    private Long getViewCount(String id){

        Object value = stringRedisTemplate.opsForHash().get("blog:viewCount", id);

        if (Objects.nonNull(value)){
            return Long.parseLong(value.toString());
        }

        return null;
    }
}
