package com.dilo.springboot.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dilo.model.Student;
import com.dilo.service.StudentService;
import com.dilo.springboot.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = StudentService.class,version = "1.0.0",timeout = 1500)
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public Student queryStudentById(Integer id) {

        Student student = studentMapper.selectByPrimaryKey(id);

        return student;
    }

    public  Integer queryAllStudentCount(){

        //提升系统性能,用户体验
        //首先去redis缓存中查询,如果有:直接使用,如果没有:去数据库中查询并放到redis缓存中
        return null;
    }
}
