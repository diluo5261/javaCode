package com.dilo.service;

import com.dilo.dao.StudentDao;
import com.dilo.domain.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    //应用类型
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }


    @Override
    public int addStudent(Student student) {
        int nums = studentDao.insertStudent(student);
        return nums;
    }

    @Override
    public List<Student> queryStudents() {
        List<Student> students = studentDao.selectStudents();
        return students;
    }
}
