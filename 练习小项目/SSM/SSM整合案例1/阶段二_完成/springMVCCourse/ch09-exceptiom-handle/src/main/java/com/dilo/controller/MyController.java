package com.dilo.controller;

import com.dilo.exception.AgeException;
import com.dilo.exception.MyUserException;
import com.dilo.exception.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;


@Controller
public class MyController {



    @RequestMapping(value = "/some.do",method = RequestMethod.POST)
    public ModelAndView doSome(String name,Integer age) throws MyUserException{

        ModelAndView mv = new ModelAndView();
        //根据请求参数，抛出异常，如果姓名不是zs时抛出异常

        if (!"zs".equals(name)){
            throw new NameException("姓名不正确！");

        }

        if (age == null || age >80){
            throw new AgeException("年龄不正确！！");
        }

        mv.addObject("myname",name);
        mv.addObject("myage",age);


        //mv.setViewName("show");

        //显示转发，可以转发到视图解析器以外的路径
        mv.setViewName("show");

        return mv;
    }





}
