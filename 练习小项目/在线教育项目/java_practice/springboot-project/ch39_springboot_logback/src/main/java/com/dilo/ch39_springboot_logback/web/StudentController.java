package com.dilo.ch39_springboot_logback.web;


import com.dilo.ch39_springboot_logback.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/student/count")
    @ResponseBody
    public String StudentCount(){
        int ret = studentService.queryStudentCount();
        log.debug("学生人数:");

        return "学生总人数为:"+ret;
    }
}
