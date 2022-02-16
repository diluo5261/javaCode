package com.dilo.web;

import com.dilo.domain.User;
import com.dilo.service.UserService;
import com.dilo.service.impl.UserServiceImpl;
import com.dilo.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class UserServlet extends  BaseServlet{



    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //处理登录业务
        //1. 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2. 调用 userService处理登录业务
        User loginUser = userService.login(new User(6, username, password, null));

        //如果等于null登录失败
        if (loginUser == null){

            //把错误信息,和回显的表单信息,保存到request域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            System.out.println("登录成功");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }

    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1. 获取参数请求
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());
        /*try {
            //把所有的数据都注入到user对象中
            BeanUtils.populate(user,req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

        //2. 检查验证码是否正确 写死检查,要求验证码为abcde

        if("abcde".equalsIgnoreCase(code)){

            //相等,检查用户名是否正确
            if(userService.existsUsername(username)){
                //不可用,返回注册页面
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("code",code);
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{
                //可用,保存到数据库,跳转到注册成功页面
                userService.registerUser(new User(5,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{
            //不相等,跳回注册页面

            req.setAttribute("msg","验证码错误");
            req.setAttribute("code",code);
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }





       /* //使用if else方法实现分类
        if("login".equals(action)){
            login(req, resp);

        }else if("regist".equals(action)){
            regist(req, resp);

        }*/

}
