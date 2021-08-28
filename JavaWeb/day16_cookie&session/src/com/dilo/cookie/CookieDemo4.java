package com.dilo.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieDemo4")
public class CookieDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //1. 创建cookie
        Cookie c1 = new Cookie("msg","setMaxAge");

        //2. 设置cookie的存活时间
        c1.setMaxAge(30);//将cookie持久化到硬盘,


        //3. 添加cookie
        response.addCookie(c1);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
