package com.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RequestDemo4", value = "/RequestDemo4")
public class RequestDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//演示获取请求头数据 referer
        String referer = request.getHeader("referer");
        System.out.println(referer);

        //防盗链操作
        if(null != referer){
            if(referer.contains("/day14")){
                System.out.println("播放电影。。。");
            }else{
                System.out.println("请看正版电影");
            }
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
