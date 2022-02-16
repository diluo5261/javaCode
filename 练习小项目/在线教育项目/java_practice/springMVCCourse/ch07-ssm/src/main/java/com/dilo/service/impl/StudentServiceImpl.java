package com.dilo.service.impl;

import com.dilo.dao.StudentDao;
import com.dilo.domain.Student;
import com.dilo.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    //使用引用类型的自动注入@AutoWired @Resource

    @Resource
    private StudentDao studentDao;


    @Override
    public int addStudent(Student student) {
        int res = studentDao.insertStudent(student);
        return res;
    }

    @Override
    public List<Student> findStudents() {
        return studentDao.selectStudents();

    }
}
