package com.mito.user.service;

import com.mito.user.pojo.po.Fans;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2024-06-10
 */
public interface FansService extends IService<Fans> {

    Long fansCount(long id);

    void addFollow(Long userId, Long followId);

    void unFollow(Long userId, Long followId);
}
