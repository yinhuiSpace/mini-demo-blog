package com.mito.blog.service.impl;

import com.mito.blog.service.ViewCountIncrService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ViewCountIncrServiceImpl implements ViewCountIncrService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void incr(String id) {

        stringRedisTemplate.opsForHash().increment("blog:viewCount",id,1);
    }
}
