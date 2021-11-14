package com.dilo.springboot.web;

import com.dilo.springboot.model.Student;
import com.dilo.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController  //相当于控制层类上加@Controller + 方法上加@ResponseBody
public class StudentController {


    @RequestMapping("/student")
    public Object student(Integer id,Integer age){
        Student student = new Student();
        student.setId(id);
        //student.setName("peter");
        student.setAge(age);
        return student;
    }

    //@RequestMapping("/student2/detail/{id}/{age}")
    @GetMapping("/student2/detail/{id}/{age}")
    public Object student2(@PathVariable("id") Integer id,
                           @PathVariable("age") Integer age){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("age",age);
        return map;
    }

    //@RequestMapping("/student2/detail/{id}/{status}")
    @DeleteMapping("/student2/detail/{id}/{age}")
    public Object student3(@PathVariable("id") Integer id,
                           @PathVariable("status") Integer status){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("status",status);
        return map;
    }

    //student2和 student3 以上代码会出现请求路径迷糊的错误
    //通常RESTful 风格中方法的请求方式会按增删查改的请求方式来区分
    //修改请求路径
    //


    @DeleteMapping("/student2/{id}/detail/{age}")
    public Object student4(@PathVariable("id") Integer id,
                           @PathVariable("status") Integer status){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("status",status);
        return map;
    }



}




