package com.dilo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Date;

//标志该类时核心配置类
@Configuration
//<context:component-scan base-package="com.dilo.domain"/>
@ComponentScan("com.dilo.domain")

@PropertySource("classpath:")
public class SpringConfiguration {

    //spring会将当前方法的返回值,以指定名称存储到spring容器中
    @Bean
    public Date getDate(){
        return new Date();
    }
}
