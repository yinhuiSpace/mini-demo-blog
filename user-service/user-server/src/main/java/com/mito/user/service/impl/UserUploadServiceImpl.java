package com.mito.user.service.impl;

import cn.hutool.core.util.IdUtil;
import com.mito.common.config.OSSProperties;
import com.mito.common.enums.RestResultEnum;
import com.mito.user.constants.ImgFileTypeConstants;
import com.mito.user.exceptions.UploadException;
import com.mito.user.service.UserUploadService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

@Service
public class UserUploadServiceImpl implements UserUploadService {

    @Resource
    COSClient cosClient;

    @Resource
    OSSProperties ossProperties;

    @Override
    public String uploadImg(MultipartFile img) {

        String contentType = img.getContentType();
        if (!ImgFileTypeConstants.PNG_TYPE.equalsIgnoreCase(contentType)
                && !ImgFileTypeConstants.JPEG_TYPE.equalsIgnoreCase(contentType)
                && !ImgFileTypeConstants.GIF_TYPE.equalsIgnoreCase(contentType)
                && !ImgFileTypeConstants.JPG_TYPE.equalsIgnoreCase(contentType)) {

            throw new UploadException(RestResultEnum.FILE_TYPE_ILLEGAL);
        }

        long size = img.getSize();
        if (size > 1000000) {
            throw new UploadException(RestResultEnum.FILE_SIZE_EXCEED_LIMIT);
        }

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);

        Long id = IdUtil.getSnowflake().nextId();
        String originalFilename = img.getOriginalFilename();
        String extension = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf('.'));
        String objectId = id + extension;

        try (InputStream inputStream = img.getInputStream()) {
            cosClient.putObject(ossProperties.getBucketName(), objectId, inputStream, objectMetadata);

            return cosClient.getObjectUrl(ossProperties.getBucketName(), objectId).toString();
        } catch (Exception e) {
            throw new UploadException(RestResultEnum.UPLOAD_ERROR);
        }
    }
}
