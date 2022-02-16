package com.dilo.ch06_springboot_multi_enviroment_properties.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping("/say")
    @ResponseBody
    public String say(){
        return "say : hello springboot";
    }

}
