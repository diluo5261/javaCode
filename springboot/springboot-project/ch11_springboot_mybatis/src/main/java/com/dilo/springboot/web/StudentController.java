package com.dilo.springboot.web;

import com.dilo.springboot.model.Student;
import com.dilo.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/student")
    @ResponseBody
    public Object student(Integer id){
        Student student = studentService.queryStudentById(id);
        System.out.println(student);
        return student;
    }
}
