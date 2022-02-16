package com.dilo.dao.impl;

import com.dilo.dao.StudentDao;
import com.dilo.domain.Student;
import com.dilo.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> selectStudent() {

        //执行SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        String sqlId = "com.dilo.dao.StudentDao.selectStudent";
        //执行sql语句,使用SqlSession类的方法
        List<Student> students = sqlSession.selectList(sqlId);

        //关闭
        sqlSession.close();
        return students;
    }

    @Override
    public int insertStudent(Student student) {

        //1. 获取session对象
        SqlSession sqlSession =MyBatisUtils.getSqlSession();
        int i = sqlSession.insert("com.dilo.dao.StudentDao.insertStudent",student);

        sqlSession.commit();
        sqlSession.close();
        return i;
    }


}
