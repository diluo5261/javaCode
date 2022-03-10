package com.dilo.gmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    //创建一个bean交给spring容器管理

    @Bean
    public CorsWebFilter corsWebFilter(){

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); //设置允许访问的网络.任意网络
        configuration.setAllowCredentials(true); // 设置是否从服务器获取cookie,允许携带cookie
        configuration.addAllowedMethod("*"); // 设置请求方法 * 表示任意.put  get  post  任意
        configuration.addAllowedHeader("*"); // 所有请求头信息 * 表示任意



        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        //第一个参数表示路径,第二个表示跨域的方式
        configurationSource.registerCorsConfiguration("/**",configuration);


        //返回当前对象
        return new CorsWebFilter(configurationSource);
    }
}
