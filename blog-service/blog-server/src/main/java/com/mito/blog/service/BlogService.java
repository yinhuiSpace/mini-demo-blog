package com.mito.blog.service;

import com.mito.blog.pojo.po.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mito.blog.pojo.vo.BlogDetailVo;
import com.mito.blog.pojo.vo.BlogListVo;
import com.mito.blog.pojo.vo.BlogVo;
import com.mito.blog.pojo.vo.HotArticleVo;

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
}
