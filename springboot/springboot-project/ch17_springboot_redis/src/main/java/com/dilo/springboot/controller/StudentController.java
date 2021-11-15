package com.dilo.springboot.controller;

import com.dilo.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

    //在控制层必须有服务层的对象
    @Autowired
    private StudentService studentService;

    @RequestMapping("/put")
    @ResponseBody
    public Object put(String key,String value){
        studentService.put(key,value);

        return "redis数据已经放入!";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(String key){
        return studentService.get(key);
    }
}
