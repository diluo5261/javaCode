package com.dilo.web.servlet;

import com.dilo.domain.User;
import com.dilo.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用UserService完成查询
        UserServiceImpl userService = new UserServiceImpl();
        List<User> users = userService.findAll();

        //2. 将list存入到request域中
        request.setAttribute("users",users);
        //3. 转发到list.jsp中
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
