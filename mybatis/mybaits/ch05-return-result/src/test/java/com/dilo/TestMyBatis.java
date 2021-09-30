package com.dilo;

import com.dilo.dao.StudentDao;
import com.dilo.domain.Student;
import com.dilo.util.MyBatisUtils;
import com.dilo.vo.QueryParam;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {
    @Test
    public void test1(){
      SqlSession sqlSession =MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = dao.selectStudentById(1001);
        System.out.println(student);
    }

    @Test
    public void test2(){
        SqlSession sqlSession =MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

       List<Student> studentList =  dao.selectMultiParam("李四",20);

       for(Student stu : studentList){
           System.out.println(stu);
       }
    }

   /* @Test
    public void testSelectCount(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        int count = dao.countStudent();

        System.out.println(count);

    }*/

    @Test
    public void testSelectLikeOne(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Student> studentList = dao.selectLikeOne("李%");

        for (Student stu : studentList){
            System.out.println(stu);
        }


    }

}
