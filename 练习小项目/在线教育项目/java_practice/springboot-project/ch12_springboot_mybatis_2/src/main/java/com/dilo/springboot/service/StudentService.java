package com.dilo.springboot.service;

import com.dilo.springboot.mapper.StudentMapper;
import com.dilo.springboot.model.Student;
import org.springframework.beans.factory.annotation.Autowired;


public interface StudentService {

    //根据id 查找学生信息
    Student queryById(Integer id);
}
