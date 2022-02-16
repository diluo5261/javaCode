package com.dilo.controller;

import com.dilo.domain.Student;
import com.dilo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService service;

    @RequestMapping("/addStudent.do")
    public ModelAndView addStudent(Student student){
        ModelAndView mv = new ModelAndView();
        String tips = "注册失败!";
        //调用service处理student
        int res = service.addStudent(student);

        if (res > 0){
        //    注册成功
            tips = "学生【" + student.getName() + "】注册成功";
        }
        //指定结果页面
        mv.addObject("tips",tips);
        mv.setViewName("result");

        return mv;
    }

    //处理查询,响应ajax
    /*
    1\加入依赖
    2、加入注解驱动
    3、加入注解
    */

    @RequestMapping("/queryStudent.do")
    @ResponseBody
    public List<Student> queryStudent(){
        //参数检查
        List<Student> studentList = service.findStudents();
        return studentList;

    }

}
