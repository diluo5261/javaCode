package com.dilo.filter;

import com.dilo.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactoinFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose(); //提交事务
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtils.rollbackAndClose(); //回滚事务
            throw new RuntimeException(e);//抛出异常给 tomcat服务器，跳转到错误页面
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
