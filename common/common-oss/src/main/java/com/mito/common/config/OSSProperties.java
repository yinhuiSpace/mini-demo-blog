package com.mito.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "oss")
public class OSSProperties {

    private String secretId;

    private String secretKey;

    private String bucketName;

    private String region;
}
