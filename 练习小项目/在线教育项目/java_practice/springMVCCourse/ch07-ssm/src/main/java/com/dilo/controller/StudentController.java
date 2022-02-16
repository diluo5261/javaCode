package com.dilo.controller;

import com.dilo.domain.Student;
import com.dilo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.xml.ws.RequestWrapper;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
//    注册学生

    @Resource
    private StudentService studentService;

    @RequestMapping("/addStudent.do")
    public ModelAndView addStudent(Student student){

        ModelAndView mv = new ModelAndView();
    //    1.调用Service处理Student
        int res = studentService.addStudent(student);
        String tips="注册失败";

        if (res >0){
        //    注册成功
            tips="学生【"+student.getName()+"】注册成功!";
        }

        mv.addObject("tips",tips);
        mv.setViewName("result");
        return mv;

    }

    //处理查询,响应ajax
    /*
    1.加依赖
    2.加注解驱动
    3.加注解
     */
    @RequestMapping("/queryStudent.do")
    @ResponseBody
    public List<Student> queryStudent(){
        List<Student> list = studentService.findStudents();
        System.out.println("444");

        return list;
    }
}
