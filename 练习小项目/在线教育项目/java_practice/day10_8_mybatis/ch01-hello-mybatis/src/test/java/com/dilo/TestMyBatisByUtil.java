package com.dilo;

import com.dilo.domain.Student;
import com.dilo.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMyBatisByUtil {

    @Test
    public void insertTest(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();


        int num = sqlSession.insert("com.dilo.dao.StudentDao.insertStudent",new Student(1004,"bob","bob@qq.com",78));

        sqlSession.commit();//提交事务
        System.out.println("成功插入"+num+"条数据");

        //关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void selectTest(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        List<Student> studentList = sqlSession.selectList("com.dilo.dao.StudentDao.selectAll");

        for (Student student : studentList){
            System.out.println(student);
        }

        sqlSession.close();
    }
}
