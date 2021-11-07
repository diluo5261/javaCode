package com.dilo.controller;

import com.dilo.pojo.Admin;
import com.dilo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminAction {
    //切记:在所有的界面层,一定会有业务逻辑层的对象
    @Autowired
    AdminService adminService;

    //实现登录判断,并进行响应的跳转
    @RequestMapping("/login.action")
    public ModelAndView login(String name, String pwd){
        Admin admin = adminService.login(name, pwd);
        ModelAndView mv =new ModelAndView();

        if (admin != null){
            //登录成功
            mv.addObject("admin",admin);
            mv.setViewName("main");

            return mv;

        }else{
            //登录失败
            mv.addObject("errmsg","用户名或密码不正确!");
            mv.setViewName("login");
            return mv;
        }

    }

}
