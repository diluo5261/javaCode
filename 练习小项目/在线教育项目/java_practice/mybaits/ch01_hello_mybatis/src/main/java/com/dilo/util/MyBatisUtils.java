package com.dilo.util;

import com.dilo.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisUtils {

    private static SqlSessionFactory factory = null;
    static{
        String config = "mybatis.xml"; //需要和项目中的文件名一致

        try {
            //1. 加载核心配置文件
            InputStream in = Resources.getResourceAsStream("mybatis.xml");

            //2.获得sqlSession 工厂对象
            factory = new SqlSessionFactoryBuilder().build(in);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取SqlSession的方法
    public static SqlSession getSqlSession(){
        SqlSession sqlSession = null;

        if (factory != null){
            //3.获取sqlSession对象
            sqlSession = factory.openSession();  //非自动提交事务
        }

        return sqlSession;
    }
}
