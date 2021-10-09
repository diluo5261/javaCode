package com.dilo;

import com.dilo.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class TestMyBatis {

    @Test
    public void test01() throws IOException {

        //1. 读取配置文件,从类的根路径开始
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        //2.创建 SqlSessionFactory 对象
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        //3.获取 Sqlsession，执行sql语句
        SqlSession sqlSession = build.openSession();
        //4.获取sql 语句,指定要执行的sql 语句标识符，sql映射文件中的 namespace + 标签的id值

        String sqlId = "com.dilo.dao.StudentDao.selectAll";
        //5.执行sqlSession的selectList
        List<Student> studentList = sqlSession.selectList(sqlId);

        for (Student student : studentList){
            System.out.println(student);
        }

        //关闭sqlsession对象
        sqlSession.close();

    }

    @Test
    public void testInsert() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");

        SqlSession sqlSession= new SqlSessionFactoryBuilder().build(inputStream).openSession();

        Student student = new Student();
        student.setAge(55);
        student.setName("hello");
        student.setEmail("hello@qq.com");
        student.setId(1004);

        int num = sqlSession.insert("com.dilo.dao.StudentDao.insertStudent",student);

        System.out.println("成功插入"+num + "条数据");
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdate() throws IOException {
        //1.读取mybatis 主配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");

        //2.获取Sqlsession 对象
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(inputStream).openSession();

        //3.获取要执行的 sql 语句
        String sqlId = "com.dilo.dao.StudentDao.updateStudent";

        //4.执行sql 语句
        Student student = new Student();
        student.setId(1004);
        student.setName("json");
        student.setEmail("null");
        student.setAge(45);

        int update = sqlSession.update(sqlId, student);
        sqlSession.commit();

        System.out.println("成功修改"+update+"条语句");
        //关闭 sqlSession
        sqlSession.close();

    }

    @Test
    public void deleteStudent() throws IOException {
        //1. 读取 mybatis 主配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");

        //2. 获取 Sqlsession 对象
        SqlSession session = new SqlSessionFactoryBuilder().build(inputStream).openSession();

        //3.获取执行sql 的语句
        String sqlId = "com.dilo.dao.StudentDao.deleteStudent";

        //4. 执行sql 语句
        int delete = session.delete(sqlId, 1004);
        session.commit();

        System.out.println("成功删除"+delete+"条语句");
        //关闭session
        session.close();

    }
}
