package com.dilo.controller;

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


    @RequestMapping(value = "/some.do",method = RequestMethod.GET)
    public ModelAndView doSome(){

        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc 的web 开发");
        mv.addObject("fun","执行的时dosome方法");

        mv.setViewName("show");

        return mv;
    }


}
