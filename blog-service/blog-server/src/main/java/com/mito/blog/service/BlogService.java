package com.mito.blog.service;

import com.mito.blog.pojo.po.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mito.blog.pojo.vo.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2024-06-03
 */
public interface BlogService extends IService<Blog> {

    List<HotArticleVo> hotArticleList();

    BlogListVo articleList(Integer categoryId, Integer pageNum, Integer pageSize);

    BlogDetailVo articleDetail(Long id);

    void createBlog(Blog blog);

    BlogListVo getPage(Long pageNum, Long pageSize, String title);

    BlogReviewListVo getReview(Long pageNum, Long pageSize, String title);

    void deleteById(Long id);

    void updateBlog(BlogUpdateVo blogUpdateVo);

    List<BlogDataVo> getData();
}
