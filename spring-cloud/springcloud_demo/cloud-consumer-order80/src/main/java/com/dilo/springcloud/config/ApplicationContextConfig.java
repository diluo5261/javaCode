package com.dilo.springcloud.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //通过注册中心获取服务列表,远程调用采用负载均衡的方式
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
