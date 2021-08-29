package com.dilo.cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/cookieDemo3")
public class CookieDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.创建多个cookie
        Cookie c1 = new Cookie("name","zhangsan");
        Cookie c2 = new Cookie("age","18");

        //2.发送Cookie
        response.addCookie(c1);
        response.addCookie(c2);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
