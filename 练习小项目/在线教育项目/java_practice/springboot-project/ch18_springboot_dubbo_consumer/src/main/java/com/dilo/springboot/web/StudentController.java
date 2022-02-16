package com.dilo.springboot.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dilo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

    //dubbo:reference interface = " " version="" chaeck=false
    @Reference(interfaceClass = StudentService.class,version = "1.0.0",check = false)
    private StudentService studentService;

    @RequestMapping("/student/count")
    @ResponseBody
    public Object studentCount(){
        int studentCount = studentService.queryAllStudentCount();
        return "学生的总数量为:"+studentCount;


    }
}
