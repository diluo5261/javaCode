package com.dilo.springboot.service;

import com.dilo.springboot.model.Student;


public interface StudentService {

    //根据id 查找学生信息
    Student queryById(Integer id);

    //根据id修改学生信息
    int updateById(Student student);
}
