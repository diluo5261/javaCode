package com.dilo.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieDemo2")
public class CookieDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //3. 获取cookie
        Cookie[] cookies = request.getCookies();

        //获取数据,遍历Cookies
        if(cookies != null){
            for (Cookie cookie : cookies){
                String name = cookie.getName();
                String value = cookie.getValue();

                System.out.println(name + " = "+ value);

            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
