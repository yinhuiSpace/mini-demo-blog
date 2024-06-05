package com.mito.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.mito.client")
public class CommentServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentServerApplication.class,args);
    }
}
