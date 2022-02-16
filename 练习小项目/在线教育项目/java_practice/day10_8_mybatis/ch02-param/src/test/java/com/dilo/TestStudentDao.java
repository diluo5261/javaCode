package com.dilo;

import com.dilo.dao.StudentDao;
import com.dilo.domain.Student;
import com.dilo.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestStudentDao {

    @Test
    public void testSelectStudentByID(){
        SqlSession session = MyBatisUtils.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);
        Student student=dao.selectStudentById(1004);
        System.out.println(student);
    }

    @Test
    public void testSelectStudentMultiParam(){
        SqlSession session = MyBatisUtils.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);

        List<Student> studentList = dao.selectStudentMultiParam("王五",20);

        for (Student student : studentList){
            System.out.println(student);
        }
    }

    @Test
    public void TestSelectStudentByObject(){
        SqlSession session = MyBatisUtils.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);

        Student student = new Student();
        student.setAge(26);
        student.setEmail("lisi@163.com");
        student.setId(1003);
        student.setName("tom");

        List<Student> studentList = dao.selectStudentByObject(student);

        for (Student stu : studentList){
            System.out.println(stu);
        }

    }

    @Test
    public void testSelectStudentMultiParamByOrder(){
        SqlSession session = MyBatisUtils.getSqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);


        List<Student> studentList = dao.selectStudentMultiParamByOrder("tom",50);

        for (Student stu : studentList){
            System.out.println(stu);
        }

    }



}
