package com.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取配置文件的上下文参数context-param
       ServletContext context = getServletConfig().getServletContext();
       String username = context.getInitParameter("username");
        System.out.println("context-param参数username的值是"+username);
        System.out.println("context-param参数password的值是"+getServletConfig().getServletContext().getInitParameter("password"));

        //2、获取当前路径,格式：/工程路径
        System.out.println("当前路径："+ context.getContextPath());

        //3、获取工程部署后在服务器硬盘上的绝对路径
        System.out.println("工程的部署的绝对路径是：" + context.getRealPath("/"));

        //获取servletContext的另一种方法，直接调用getServletContext（）
        ServletContext context1 = getServletContext();
        context1.setAttribute("key1","value1");
        System.out.println("context1 中获取域数据" + context1.getAttribute("key1"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
