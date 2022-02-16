package com.dilo;

import com.dilo.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {

    @org.junit.Test
    public void test() throws IOException {
        //1。 读取mybatis 主配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");

        //2. 获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        //3.获取SqlSession 对象，用来执行sql语句
        SqlSession session = factory.openSession();

        //4.获取sqlid
        String sqlId = "com.dilo.dao.StudentDao.selectAll";

        //5.执行 sql 语句
        List<Student> studentList = session.selectList(sqlId);

        for (Student student : studentList){
            System.out.println(student);
        }

    }
}
