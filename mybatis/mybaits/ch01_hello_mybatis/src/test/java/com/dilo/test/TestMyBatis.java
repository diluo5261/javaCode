package com.dilo.test;

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
    public void test1() throws IOException {
        //1. 加载核心配置文件
        InputStream in = Resources.getResourceAsStream("mybatis.xml");

        //2.获得sqlSession 工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        //3.获取sqlSession对象
        SqlSession sqlSession = factory.openSession();

        //4.执行sql语句
        int numbers = sqlSession.insert("StudentDao.insertStudent",new Student(1003,"王五","3464@163.com",50));
//        List<Student> students = sqlSession.selectList("StudentDao.selectStudents");


        //mybatis默认不是自动提交事务的,要在 inert \ update  \ delete 后要手动提交事务
        sqlSession.commit();
        // 5.打印结果
        System.out.println(numbers);
        //6.释放资源
        sqlSession.close();

    }
}
