package com.dilo.web;

import com.dilo.domain.User;
import com.dilo.service.UserService;
import com.dilo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //2.检查验证码是否正确,现在先写死,验证验证码 是否为 abcde

        if ("abcde".equals(code)){
            //验证码正确
            //3.检查用户名是否可用
            if (userService.existsUserName(username)){
                //用户名不可用
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("password",password);
                req.setAttribute("email",email);
                req.setAttribute("code",code);
                System.out.println("用户名"+username+"已存在!");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{
                //用户名可用
                //调用service保存到数据库
                userService.registerUser(new User(null,username,password,email));
                //注册成功,跳转到成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);

            }

        }else{
            //验证码不正确
            //跳回注册页面
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("email",email);
            req.setAttribute("code",code);
            System.out.println("验证码"+code+"错误!");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }


    }
}
