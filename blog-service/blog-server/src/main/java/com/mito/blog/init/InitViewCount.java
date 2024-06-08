package com.mito.blog.init;

import com.mito.blog.pojo.po.Blog;
import com.mito.blog.service.BlogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class InitViewCount implements CommandLineRunner {

    @Resource
    BlogService blogService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("初始化博客浏览量到redis......");
        List<Blog> blogs = blogService.list();
        Map<String, String> viewCountMap = blogs.stream().collect(Collectors.toMap(
                new Function<Blog, String>() {
                    @Override
                    public String apply(Blog blog) {
                        return blog.getId().toString();
                    }
                },
                new Function<Blog, String>() {
                    @Override
                    public String apply(Blog blog) {
                        return blog.getViewCount().toString();
                    }
                })
        );

        stringRedisTemplate.opsForHash().putAll("blog:viewCount",viewCountMap);
    }
}
