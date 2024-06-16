package com.mito.blog.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImgUploadService {

    String blogImg(MultipartFile blogImg);

}
