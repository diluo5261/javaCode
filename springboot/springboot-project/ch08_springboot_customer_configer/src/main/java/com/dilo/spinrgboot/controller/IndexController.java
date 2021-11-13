package com.dilo.spinrgboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Value("${school.name}")
    private String schoolName;

    @Value("${website}")
    private String website;

    @RequestMapping("/say")
    @ResponseBody
    public String say(){
        return "hello ch08 springboot school.name="+schoolName+"website = "+website;
    }
}
