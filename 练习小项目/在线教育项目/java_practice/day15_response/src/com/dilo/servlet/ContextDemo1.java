package com.dilo.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/contextDemo1")
public class ContextDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过requset获取
        ServletContext context1 = request.getServletContext();

        //通过HttpServlet获取
        ServletContext context2 = this.getServletContext();

        //3. 定义文件名称
        String fileName = "a.jpg";
        //4.获取Mime类型
        String mimeType = context2.getMimeType(fileName);
        System.out.println(mimeType);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
