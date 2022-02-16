package com.dilo.springboot.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dilo.springboot.model.Student;
import com.dilo.springboot.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    @Reference(interfaceClass = StudentService.class,version = "1.0.0",check = false)
    private StudentService studentService;

    @RequestMapping("/student/detail")
    public ModelAndView studentDetail(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Student student = studentService.queryStudentById(id);
        modelAndView.addObject("student",student);

        modelAndView.setViewName("studentDetail");
        return modelAndView;
    }
}
