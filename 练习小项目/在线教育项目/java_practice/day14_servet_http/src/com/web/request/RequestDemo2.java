package com.web.request;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "RequestDemo2", value = "/RequestDemo2")
public class RequestDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //演示获取请求头数据
        //1、获取说有请求头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        //2、遍历
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name +"---"+value);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
