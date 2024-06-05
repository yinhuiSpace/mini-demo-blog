package com.mito.client;

import com.mito.client.pojo.po.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service",contextId = "userClient")
public interface UserClient {

    @GetMapping("/user-service/user/one")
    User getOneById(@RequestParam("id") Long id);

}
