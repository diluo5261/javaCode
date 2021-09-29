package com.dilo;

import com.dilo.dao.StudentDao;
import com.dilo.dao.impl.StudentDaoImpl;
import com.dilo.domain.Student;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {
    @Test
    public void test1(){
        StudentDao dao = new StudentDaoImpl();

        List<Student> studentList = dao.selectStudent();

        for(Student stu : studentList){
            System.out.println(stu);
        }
    }

    @Test
    public void test2(){
        StudentDao studentDao = new StudentDaoImpl();
        int i = studentDao.insertStudent(new Student(123, "tom", "tom@163.com", 85));
        System.out.println("成功插入"+i+"条数据");

        List<Student> list = studentDao.selectStudent();
        for (Student student:list){
            System.out.println(student);
        }

    }
}
