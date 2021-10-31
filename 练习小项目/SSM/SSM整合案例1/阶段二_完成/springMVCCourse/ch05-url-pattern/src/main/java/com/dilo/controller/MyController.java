package com.dilo.controller;

import com.dilo.domain.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Controller : 创建处理器对象，对象放在springmvc容器中
 * 位置：放在类的上面
 * 能处理请求的都是控制器(处理器) : MyController 能处理请求,叫做后端控制器(back controller)
 */
@Controller
public class MyController {



    @RequestMapping("/some.do")
    public ModelAndView doSome(String name, Integer age){
        System.out.println("doSome name="+name+"age="+age);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("myname",name);
        modelAndView.addObject("myage",age);

        modelAndView.setViewName("show");

        return modelAndView;
    }








}
