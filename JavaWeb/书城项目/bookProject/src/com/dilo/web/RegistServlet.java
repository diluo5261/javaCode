package com.dilo.web;

import com.dilo.domain.User;
import com.dilo.service.UserService;
import com.dilo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取参数请求
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //2. 检查验证码是否正确 写死检查,要求验证码为abcde

        if("abcde".equalsIgnoreCase(code)){

            //相等,检查用户名是否正确
            if(userService.existsUsername(username)){
                //不可用,返回注册页面
                System.out.println("用户名"+username+"已存在");
                req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
            }else{
                //可用,保存到数据库,跳转到注册成功页面
                userService.registerUser(new User(5,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.html").forward(req,resp);
            }
        }else{
            //不相等,跳回注册页面
            System.out.println("验证码错误!");
            req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
        }
    }
}
