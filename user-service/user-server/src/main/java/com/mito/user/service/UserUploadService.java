package com.mito.user.service;

import org.springframework.web.multipart.MultipartFile;

public interface UserUploadService {

    String uploadImg(MultipartFile img);

}
