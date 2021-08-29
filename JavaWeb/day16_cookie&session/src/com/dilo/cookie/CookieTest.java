package com.dilo.cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应的消息体的数据格式以及编码
        response.setContentType("text/html;charset=utf-8");
        //1. 获取所有的cookie
        Cookie[] cookies = request.getCookies();

        boolean flag = false;
        //2. 遍历cookie
        if(cookies != null && cookies.length >0){
           for (Cookie cookie : cookies){
               //3. 获取cookie的名称
               String name = cookie.getName();
               if("lastname".equals(name)){
                   //有该cookie,不是第一次访问

                   //响应数据


                   //获取Cookie的value
                   String value = cookie.getValue();
                   value = URLDecoder.decode(value,"utf-8");
                   response.getWriter().write("欢迎回来,您上次访问时间为 :　"+value);

                   //设置cookie的值,
                   Date date = new Date();
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String str_date = sdf.format(date);

                    //中间有空格,进行URL编码操作
                   str_date = URLEncoder.encode(str_date,"utf-8");
                    cookie.setValue(str_date);

                    //设置cookie的存储时间
                   cookie.setMaxAge(60*60*24*30);
                    response.addCookie(cookie);
                    flag = true;
                    break;
               }
           }
        }

        if(cookies == null || cookies.length ==0 ||flag == false){
            //设置cookie的值,
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String str_date = sdf.format(date);

            str_date = URLEncoder.encode(str_date,"utf-8");
            Cookie c1 = new Cookie("lastname",str_date);

            c1.setMaxAge(60*60*24*30);
            response.addCookie(c1);

            response.getWriter().write("欢迎您首次访问");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
