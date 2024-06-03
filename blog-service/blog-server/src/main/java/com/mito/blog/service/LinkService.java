package com.mito.blog.service;

import com.mito.blog.pojo.po.Link;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mito.blog.pojo.vo.LinkVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2024-06-03
 */
public interface LinkService extends IService<Link> {

    List<LinkVo> getAllLinks();
}
