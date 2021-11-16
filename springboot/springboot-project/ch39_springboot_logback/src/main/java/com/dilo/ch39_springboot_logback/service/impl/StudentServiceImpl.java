package com.dilo.ch39_springboot_logback.service.impl;
import com.dilo.ch39_springboot_logback.mapper.StudentMapper;
import com.dilo.ch39_springboot_logback.service.StudentService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;



    @Override
    public int queryStudentCount() {
        return studentMapper.queryStudentCount();
    }
}
