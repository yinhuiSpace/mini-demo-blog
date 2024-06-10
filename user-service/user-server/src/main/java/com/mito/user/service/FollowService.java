package com.mito.user.service;

import com.mito.user.pojo.po.Follow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2024-06-10
 */
public interface FollowService extends IService<Follow> {

    Long followedCount(Long userId);

    void addFollow(Long userId, Long l);

    boolean isFollow(Long userId,Long followId);

    void unFollow(Long userId, Long followId);
}
