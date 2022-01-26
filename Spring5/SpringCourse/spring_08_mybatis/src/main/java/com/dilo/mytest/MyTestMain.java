package com.dilo.mytest;

import com.dilo.dao.UserDao;
import com.dilo.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTestMain {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();


        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.selectLikeFirst("%张三%");
        System.out.println(userList);
    }
}
