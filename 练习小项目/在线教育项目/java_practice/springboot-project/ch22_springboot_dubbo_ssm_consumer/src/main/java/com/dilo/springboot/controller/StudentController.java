package com.dilo.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dilo.model.Student;
import com.dilo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    @Reference(interfaceClass = StudentService.class ,version = "1.0.0",check = false)
    private StudentService studentService;

    @RequestMapping("/student/detail/{id}")
    public ModelAndView studentDetail(@PathVariable("id") Integer id){

        ModelAndView modelAndView = new ModelAndView();
        Student student =  studentService.queryStudentById(id);
        modelAndView.addObject("student",student);

        return modelAndView;

    }


}
