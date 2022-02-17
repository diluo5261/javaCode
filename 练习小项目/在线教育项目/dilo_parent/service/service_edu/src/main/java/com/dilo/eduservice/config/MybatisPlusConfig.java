package com.dilo.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    //逻辑删除
    /*@Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }*/

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    //乐观锁
    @Bean
    public OptimisticLockerInterceptor mybatisPlusInterceptor() {

        return new OptimisticLockerInterceptor();
    }


}
