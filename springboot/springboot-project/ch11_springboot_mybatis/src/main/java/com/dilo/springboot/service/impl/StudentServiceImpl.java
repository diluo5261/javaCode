package com.dilo.springboot.service.impl;

import com.dilo.springboot.mapper.StudentMapper;
import com.dilo.springboot.model.Student;
import com.dilo.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student queryStudentById(Integer id){
        Student student = studentMapper.selectByPrimaryKey(id);
        return student;
    }
}
