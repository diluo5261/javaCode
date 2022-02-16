package com.dilo.test;

import com.dilo.domain.Student;
import com.dilo.util.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyApp2 {


@Test
    public void main1() throws IOException {


        //3.获取sqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        //4.执行sql语句
        List<Student> students = sqlSession.selectList("StudentDao.selectStudents");

        //5.打印结果
        System.out.println(students);
        //6.释放资源
        sqlSession.close();
    }
    public static void main(String[] args) throws IOException {
        //访问mybaits读取student数据
        //1. 定义mybatis注位置文件的名称,从路径的跟开始(target/classes)
        String config= "mybatis.xml";
        //2.读取这个config表示的文件
        InputStream in = Resources.getResourceAsStream(config);

        //3. 创建 SQLSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        //4. 创建SqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);

        //5.[重要]获取sqlSession对象,从SqlSessionFactory中获取 SqlSession
        SqlSession sqlSession = factory.openSession();

        //6.指定要执行sql语句的标识,使用sql映射文件中的namespace + . + 标签的id值
        String sqlId = "StudentDao" +"."+"selectStudents";

        //7.执行sql语句,通过sqlId找到语句
        List<Student> studentList = sqlSession.selectList(sqlId);

        //8.输出结果
        for (Student s: studentList) {
            System.out.println(s);
        }

        //9.关闭sqlSession对象
        sqlSession.close();
    }
}
