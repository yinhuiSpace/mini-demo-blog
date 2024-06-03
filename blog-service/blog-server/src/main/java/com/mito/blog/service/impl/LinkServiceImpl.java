package com.mito.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mito.blog.constants.LinkConstants;
import com.mito.blog.pojo.po.Link;
import com.mito.blog.mapper.LinkMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mito.blog.pojo.vo.LinkVo;
import com.mito.blog.service.LinkService;
import com.mito.common.utils.BeanCopyUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author root
 * @since 2024-06-03
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public List<LinkVo> getAllLinks() {

        //1.已通过审核
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Link::getStatus, LinkConstants.LINK_NORMAL);

        List<Link> linkList = list(wrapper);


        return linkList.stream().map(new Function<Link, LinkVo>() {
            @Override
            public LinkVo apply(Link link) {

                return BeanCopyUtil.copyBean(link, LinkVo.class);
            }
        }).collect(Collectors.toList());
    }
}
