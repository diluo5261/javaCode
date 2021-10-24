package com.dilo.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BmiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //接收请求参数
        String name = req.getParameter("name");
        String height = req.getParameter("h");
        String weight = req.getParameter("w");

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
        req.setAttribute("msg",msg);

        req.getRequestDispatcher("/result.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
