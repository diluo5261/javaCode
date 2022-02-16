package com.dilo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MyController {


    @RequestMapping(value = "/some.do",method = RequestMethod.POST)
    public ModelAndView doSome(String name,Integer age){
        System.out.println("myControl中的doSome方法！");

        ModelAndView mv = new ModelAndView();

        mv.addObject("myname",name);
        mv.addObject("myage",age);

        mv.setViewName("show");

        return mv;
    }





}
