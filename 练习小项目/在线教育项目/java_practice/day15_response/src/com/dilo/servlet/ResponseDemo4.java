package com.dilo.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/responseDemo4")
public class ResponseDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取流对象之前，设置流的默认编码：ISO-8859-1  设置为gbk
//        response.setCharacterEncoding("gbk");
        //告诉浏览器
        response.setHeader("content-type","text/html;charset=utf-8");

        //简单的形式设置编码
        response.setContentType("text/html;charset=utf-8");

        //1、获取字符输出流
        PrintWriter pw = response.getWriter();
        //2、输出数据
        pw.write("你好，hello");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
