package com.dilo.service.impl;

import com.dilo.dao.StudentDao;
import com.dilo.domain.Student;
import com.dilo.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = null;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int addStudent(Student student) {
        int nums = studentDao.insertStudent(student);
        return nums;
    }

    @Override
    public List<Student> queryStudent() {
        List<Student> studentList = studentDao.selectStudents();
        return studentList;
    }
}
