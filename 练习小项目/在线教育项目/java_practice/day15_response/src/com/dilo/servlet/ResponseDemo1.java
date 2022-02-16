package com.dilo.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/responseDemo1")
public class ResponseDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo1...");
        /*//1、设置状态码为302
        response.setStatus(302);
        //2、设置响应头location
        response.setHeader("location","/day15_response/responseDemo2");*/

        //简单的重定向方法
        response.sendRedirect("/day15_response/responseDemo2");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
