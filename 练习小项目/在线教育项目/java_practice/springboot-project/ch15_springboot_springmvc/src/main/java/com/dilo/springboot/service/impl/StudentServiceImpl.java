package com.dilo.springboot.service.impl;

import com.dilo.springboot.mapper.StudentMapper;
import com.dilo.springboot.model.Student;
import com.dilo.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    //创建持久层对象
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student queryById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int updateById(Student student) {


        return studentMapper.updateByPrimaryKeySelective(student);
    }
}
