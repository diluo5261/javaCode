package com.dilo.vodservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VideoConstantPropertiesUtil implements InitializingBean {

    @Value("${spring.oss.file.accessKeyId}")
    private String accessKeyId;

    @Value("${spring.oss.file.accessKeySecret}")
    private String accessKeySecret;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET= accessKeySecret;
    }
}
