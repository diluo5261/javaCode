package com.dilo;

import com.dilo.dao.StudentDao;
import com.dilo.domain.Student;
import com.dilo.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {
    @Test
    public void test1(){
       /*
           使用mybatis的动态代理机制,使用SqlSession.get<apper(dao接口)
           getMapper 能获取dao接口对应的实现类对象
        */

        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.selectStudent();
        for (Student student : students){
            System.out.println("学生"+student);
        }
    }

    @Test
    public void test2(){

        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        int num = dao.insertStudent(new Student(145, "json", "json@163.com", 55));
        sqlSession.commit();
        
        System.out.println("成功插入"+num + "条数据");


    }
}
