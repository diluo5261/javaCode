package com.dilo.springboot.web;

import com.dilo.springboot.model.Student;
import com.dilo.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

    //创建service对象
    @Autowired
    private StudentService studentService;

    @RequestMapping("/student")
    @ResponseBody
    public Student queryById(Integer id){
        return studentService.queryById(id);


    }
}
