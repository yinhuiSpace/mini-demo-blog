package com.mito.user.controller;

import cn.hutool.core.util.IdUtil;
import com.mito.common.config.OSSProperties;
import com.mito.common.result.RestResult;
import com.mito.user.service.UserUploadService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserUploadController {

    @Resource
    UserUploadService userUploadService;

    @PostMapping("/upload")
    public Object upload(@RequestParam("img") MultipartFile img) {

        return RestResult.ok(userUploadService.uploadImg(img));
    }

}
