package com.mito.blog.service.impl;

import cn.hutool.core.util.IdUtil;
import com.mito.blog.constants.ImgFileTypeConstants;
import com.mito.blog.exception.UploadException;
import com.mito.blog.service.ImgUploadService;
import com.mito.common.config.OSSProperties;
import com.mito.common.enums.RestResultEnum;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Objects;

@Service
public class ImgUploadServiceImpl implements ImgUploadService {


    @Resource
    COSClient cosClient;

    @Resource
    OSSProperties ossProperties;

    @Override
    public String blogImg(MultipartFile blogImg) {

        String contentType = blogImg.getContentType();
        if (!ImgFileTypeConstants.PNG_TYPE.equalsIgnoreCase(contentType)
                && !ImgFileTypeConstants.JPEG_TYPE.equalsIgnoreCase(contentType)
                && !ImgFileTypeConstants.GIF_TYPE.equalsIgnoreCase(contentType)
                && !ImgFileTypeConstants.JPG_TYPE.equalsIgnoreCase(contentType)) {

            throw new UploadException(RestResultEnum.FILE_TYPE_ILLEGAL);
        }

        long size = blogImg.getSize();
        if (size > 1000000) {
            throw new UploadException(RestResultEnum.FILE_SIZE_EXCEED_LIMIT);
        }

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);

        Long id = IdUtil.getSnowflake().nextId();
        String originalFilename = blogImg.getOriginalFilename();
        String extension = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf('.'));
        String objectId = id + extension;

        try (InputStream inputStream = blogImg.getInputStream()) {
            cosClient.putObject(ossProperties.getBucketName(), objectId, inputStream, objectMetadata);

            return cosClient.getObjectUrl(ossProperties.getBucketName(), objectId).toString();
        } catch (Exception e) {
            throw new UploadException(RestResultEnum.UPLOAD_ERROR);
        }
    }
}
