package com.mito.client;

import com.mito.client.pojo.po.Blog;
import com.mito.common.result.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "blog-service",contextId = "blogClient")
public interface BlogClient {


    @GetMapping("/blog-service/article/count")
    RestResult<Long> count(@RequestParam("id")Long id);

    @GetMapping("/blog-service/article/one")
    Blog getOne(@RequestParam("id")Long id);
}
