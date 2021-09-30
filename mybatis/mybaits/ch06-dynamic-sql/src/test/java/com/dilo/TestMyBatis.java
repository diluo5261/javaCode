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
    public void testIf(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("张三");
        student.setAge(30);
        List<Student> studentList = dao.selectStudentIf(student);

        for (Student stu : studentList){
            System.out.println(stu);
        }
    }

    @Test
    public void testWhere(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("张三");
        student.setAge(30);
        List<Student> studentList = dao.selectStudentIf(student);

        for (Student stu : studentList){
            System.out.println(stu);
        }
    }

}
