package com.dilo.springboot.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dilo.springboot.mapper.StudentMapper;
import com.dilo.springboot.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = StudentService.class,version = "1.0.0",timeout = 15000)
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student queryStudentById(Integer id) {


        return studentMapper.selectByPrimaryKey(id);
    }
}
