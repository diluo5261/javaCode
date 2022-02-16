package com.dilo.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping("/say")
    @ResponseBody
    public String Say(){
        return "say : hello springBoot!";
    }

    @RequestMapping("/mapvalue")
    @ResponseBody
    public  Map<String,Object> mapValue(){
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("message","springboot Project!");
        return retMap;
    }
}
