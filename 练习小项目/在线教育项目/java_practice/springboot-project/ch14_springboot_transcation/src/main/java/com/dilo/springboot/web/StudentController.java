package com.dilo.springboot.web;

import com.dilo.springboot.model.Student;
import com.dilo.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
public class StudentController {

    //创建service对象
    @Autowired
    private StudentService studentService;

    @RequestMapping("/query")
    @ResponseBody
    public Student queryById(Integer id){
        return studentService.queryById(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object updateById(Integer id,String name){
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        int res = studentService.updateById(student);
        int i = 10/0;

        return queryById(id);
    }
}
