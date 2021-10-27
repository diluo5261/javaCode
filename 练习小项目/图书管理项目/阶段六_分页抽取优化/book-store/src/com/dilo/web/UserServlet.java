package com.dilo.web;

import com.dilo.domain.User;
import com.dilo.service.UserService;
import com.dilo.service.impl.UserServiceImpl;
import com.dilo.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //调用userService.login();
        User login = service.login(new User(null, username, password, null));

        if (login == null){
            //   登录失败
            System.out.println("登录失败");
            request.setAttribute("username",username);
            request.setAttribute("password",password);
            request.setAttribute("msg","用户名或密码错误!");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);

        }else{
            //    登录成功
            System.out.println("登录成功");
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        //User user = WebUtils.copyParamTOBean(request.getParameterMap(),new User());
        //System.out.println(user);


        //2.检查验证码是否正确,现在先写死,验证验证码 是否为 abcde

        if ("abcde".equals(code)){
            //验证码正确
            //3.检查用户名是否可用
            if (service.existsUserName(username)){
                //用户名不可用
                request.setAttribute("msg","用户名已存在");
                request.setAttribute("username",username);
                request.setAttribute("password",password);
                request.setAttribute("email",email);
                request.setAttribute("code",code);
                System.out.println("用户名"+username+"已存在!");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else{
                //用户名可用
                //调用service保存到数据库
                User user = WebUtils.copyParamTOBean(request.getParameterMap(),new User());
                service.registerUser(user);
                //注册成功,跳转到成功页面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);

            }

        }else{
            //验证码不正确
            //跳回注册页面
            request.setAttribute("msg","验证码错误");
            request.setAttribute("username",username);
            request.setAttribute("password",password);
            request.setAttribute("email",email);
            request.setAttribute("code",code);
            System.out.println("验证码"+code+"错误!");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
    }


}
