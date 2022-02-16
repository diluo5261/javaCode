package com.web.request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/RequestDemo8")
public class RequestDemo8 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Demo8888被访问了。。。。。。。。");
        //转发到demo9
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/RequestDemo9");
//        requestDispatcher.forward(request,response);

        //存储数据
        request.setAttribute("name","zhangsan");
        request.getRequestDispatcher("/RequestDemo9").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
