package com.dilo;

import com.dilo.dao.UserDao;
import com.dilo.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class MyTest {

    @Test
    public void selectLikeFirstTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();


        UserDao userDao = sqlSession.getMapper(UserDao.class);
       List<User> userList = userDao.selectLikeFirst("张三");
        System.out.println(userList);

    }


    @Test
    public void findByDiffFiledTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();


        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user1 = userDao.findByDiffFiled("name","张三");
        User user2 = userDao.findByDiffFiled("age","50");

    }

    @Test
    public void selectMultiNickTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();




        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.selectUserByNick("%张三%");
        System.out.println(userList);

    }
    @Test
    public void selectMultiMapTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();


        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name","bob");
        hashMap.put("age",50);

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.selectMultiMap(hashMap);
        System.out.println(userList);

    }

    @Test
    public void selectMultiPosTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();



        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.selectMultiPos("bob",50);
        System.out.println(userList);

    }

    @Test
    public void selectMultiObjectTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();

        User user = new User();
        user.setName("bob");
        user.setAge(50);

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.selectMultiObject(user);

        System.out.println(userList);

    }

    @Test
    public void selectMultiParamTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.selectMultiParam("bob",50);

        System.out.println(userList);

    }

    @Test
    public void selectOneTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
       User user= userDao.selectOne(1003);
        System.out.println(user);

    }

    @Test
    public void queryAllTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.selectStudents();

        System.out.println(userList);

    }

    @Test
    public void insertTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();

        User user = new User();
        user.setName("范冰冰");
        user.setAge(55);

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int res = userDao.insert(user);
        sqlSession.commit();
        System.out.println(res >0 ?"插入成功":"插入失败!");
    }

    @Test
    public void updateTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();

        User user = new User();
        user.setName("范冰冰");
        user.setAge(563);

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int res = userDao.update(user);
        sqlSession.commit();
        System.out.println(res >0 ?"修改成功":"修改失败!");
    }

    @Test
    public void deleteTest() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();

        UserDao userDao = session.getMapper(UserDao.class);
        int res = userDao.delete(1010);

        session.commit();
        System.out.println(res >0 ?"删除成功":"删除失败!");
    }
}
