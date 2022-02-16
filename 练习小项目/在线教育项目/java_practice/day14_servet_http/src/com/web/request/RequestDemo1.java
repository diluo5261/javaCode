package com.web.request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RequestDemo1", value = "/RequestDemo1")
public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求方式
        String method = request.getMethod();
        System.out.println(method);

        //2、获取虚拟目录
        System.out.println(request.getContextPath());

        //3、获取Servlet路径
        System.out.println(request.getServletPath());

        //4、获取请求的参数
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
