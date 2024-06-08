package com.mito.blog.runner;

import com.mito.blog.pojo.po.Blog;
import com.mito.blog.service.BlogService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UpdateViewCount {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    BlogService blogService;

    @Scheduled(cron = "* 30 * * * ?")
    public void updateViewCount() {

        //将redis数据写入mysql
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("blog:viewCount");

        List<Blog> blogList = entries.entrySet().stream().map(new Function<Map.Entry<Object, Object>, Blog>() {
            @Override
            public Blog apply(Map.Entry<Object, Object> entry) {

                Blog blog = new Blog();
                blog.setId(Long.parseLong(entry.getKey().toString()))
                        .setViewCount(Long.parseLong(entry.getValue().toString()));

                return blog;
            }
        }).collect(Collectors.toList());

        blogService.updateBatchById(blogList);
    }
}
