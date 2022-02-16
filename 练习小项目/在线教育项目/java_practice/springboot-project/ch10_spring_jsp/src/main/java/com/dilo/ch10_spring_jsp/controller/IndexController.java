package com.dilo.ch10_spring_jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping("/say")
    public ModelAndView say(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","hello Springboot!");
        modelAndView.setViewName("say");
        System.out.println("hello");

        return modelAndView;

    }
}
