package com.dilo.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @RequestMapping("/message")
    public ModelAndView message(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","nihao message");
        modelAndView.setViewName("message");

        return modelAndView;
    }
}
