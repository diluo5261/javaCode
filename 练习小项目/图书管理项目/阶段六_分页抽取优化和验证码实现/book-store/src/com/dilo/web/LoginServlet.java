package com.dilo.web;

import com.dilo.domain.User;
import com.dilo.service.UserService;
import com.dilo.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //调用userService.login();
        User login = service.login(new User(null, username, password, null));

        if (login == null){
            //   登录失败
            System.out.println("登录失败");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);

        }else{
        //    登录成功
            System.out.println("登录成功");
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }
}
