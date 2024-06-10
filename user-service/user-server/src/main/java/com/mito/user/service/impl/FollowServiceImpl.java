package com.mito.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mito.common.enums.RestResultEnum;
import com.mito.user.constants.EnableConstants;
import com.mito.user.exceptions.FollowException;
import com.mito.user.pojo.po.Fans;
import com.mito.user.pojo.po.Follow;
import com.mito.user.mapper.FollowMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mito.user.pojo.po.User;
import com.mito.user.service.FollowService;
import com.mito.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author root
 * @since 2024-06-10
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    @Resource
    UserService userService;

    @Override
    public Long followedCount(Long userId) {

        LambdaQueryWrapper<Follow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Follow::getUserId,userId);

        return count(wrapper);
    }

    @Override
    public void addFollow(Long userId, Long followId) {

        //检查目标是否可用
        if (Objects.equals(userId, followId)){
            throw new FollowException(RestResultEnum.CAN_NOT_SELF_FOLLOW);
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId,followId)
                .eq(User::getStatus, EnableConstants.ENABLE_STATUS);

        long count = userService.count(wrapper);

        if (count!=1){
            throw new FollowException(RestResultEnum.CAN_NOT_FOLLOW);
        }

        //检查是否能关注
        LambdaQueryWrapper<Follow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Follow::getUserId,userId)
                .eq(Follow::getFollowedId,followId);

        if (count(queryWrapper)!=0){
            throw new FollowException(RestResultEnum.CAN_NOT_REPEAT_FOLLOW);
        }

        Follow follow = new Follow();
        follow.setUserId(userId)
                        .setFollowedId(followId);

        save(follow);
    }

    @Override
    public boolean isFollow(Long userId, Long followId) {

        LambdaQueryWrapper<Follow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Follow::getUserId,userId)
                .eq(Follow::getFollowedId,followId);

        return count(wrapper)!=0;
    }

    @Override
    public void unFollow(Long userId, Long followId) {

        LambdaQueryWrapper<Follow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Follow::getUserId,userId)
                .eq(Follow::getFollowedId,followId);

        remove(wrapper);
    }
}
