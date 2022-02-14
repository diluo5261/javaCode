package com.dilo.ossservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${spring.oss.file.endpoint}")
    private String endpoint;

    @Value("${spring.oss.file.accessKeyId}")
    private String accessKeyId;

    @Value("${spring.oss.file.accessKeySecret}")
    private String accessKeySecret;

    @Value("${spring.oss.file.bucketName}")
    private String bucketName;


    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;


    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET= accessKeySecret;
        BUCKET_NAME = bucketName;
    }
}
