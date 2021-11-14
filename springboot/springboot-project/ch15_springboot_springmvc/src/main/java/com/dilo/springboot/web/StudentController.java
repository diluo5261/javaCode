package com.dilo.springboot.web;

import com.dilo.springboot.model.Student;
import com.dilo.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController  //相当于控制层类上加@Controller + 方法上加@ResponseBody
//                意味着当前控制层类中所有方法返回的都是 JSON 对象

public class StudentController {

    //创建service对象
    @Autowired
    private StudentService studentService;

    @RequestMapping("/student")
    //@ResponseBody
    public Object student(){
        Student student = new Student();
        student.setId(1001);
        student.setName("peter");
        student.setAge(26);
        return student;
    }

    @RequestMapping(value = "/queryById", method= RequestMethod.GET)
    @GetMapping("queryById")        //两个注解的作用相同，只能接收 get请求，如果请求方式不对，会报405错误
    //该注解通常在查询数据时使用

    public Object queryStudentBuId(){
        Student student = new Student();

        return student;
    }

    //@PostMapping


}




