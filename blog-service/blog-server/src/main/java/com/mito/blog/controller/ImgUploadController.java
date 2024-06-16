package com.mito.blog.controller;

import com.mito.blog.service.ImgUploadService;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class ImgUploadController {

    @Resource
    ImgUploadService imgUploadService;

    @PostMapping("/img")
    public Object uploadImg(@RequestParam("blogImg")MultipartFile blogImg){

        return RestResult.ok().setContent(imgUploadService.blogImg(blogImg));
    }

}
