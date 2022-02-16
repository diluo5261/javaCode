package com.dilo.springboot.ch07_springboot_multi_enviroment_yml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping("/say")
    @ResponseBody
    public String say(){
        return "hello ch07 springboot";
    }
}
