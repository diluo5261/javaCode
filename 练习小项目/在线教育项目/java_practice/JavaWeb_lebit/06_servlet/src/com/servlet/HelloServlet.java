package com.servlet;

import javax.servlet.*;
import java.io.IOException;

public class HelloServlet implements Servlet {
    public HelloServlet() {
        System.out.println("构造器");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("初始化方法");

        //1、可以获取Servlet程序的别名
        System.out.println("Servlet 程序的别名是：" + servletConfig.getServletName());


        //2、获取初始化参数
        System.out.println(servletConfig.getInitParameter("url"));

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * service是专门处理请求和响应的
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        System.out.println("Hello Servlet 被访问了");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("销毁方法");

    }
}
