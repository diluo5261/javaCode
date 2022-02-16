package com.dilo.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class BmiAjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("接收了ajax的异步请求");
        /*//接收请求参数
        String name = request.getParameter("name");
        String height = request.getParameter("h");
        String weight = request.getParameter("w");

        //计算BMI= 体重/身高的平方
        double h = Double.valueOf(height);
        double w = Double.valueOf(weight);

        double bmi = w /(h*h);

        //判断bmi的范围

        String msg="";

        if (bmi > 27){
            msg = "胖";
        }else if(bmi > 24){
            msg = "比较胖";
        }else if(bmi > 18.5){
            msg = "正常";
        }else{
            msg= "比较瘦";
        }


        msg = "你好 : "+name +"先生/女士,您的bmi值是 :"+bmi+"你的身体"+msg;
        request.setAttribute("msg",msg);

        request.getRequestDispatcher("/result.jsp").forward(request,response);*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
