package com.dilo.gmall.all.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PassportController {

    @GetMapping("/login.html")
    public  String login(HttpServletRequest request){
        //获取到路径
        String originUrl = request.getParameter("originUrl");
        //当用户再某个url点击登录,用户输入正确的用户名,密码,回跳到原来的页面
        request.setAttribute("originUrl",originUrl);
        return "login";
    }
}
