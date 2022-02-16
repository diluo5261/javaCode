package com.dilo.ch04_springboot_properties_yml_yaml.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping("/say")
    @ResponseBody
    public Object say(String message){
        return "Say:Hello springboot!"+message;
    }



}
