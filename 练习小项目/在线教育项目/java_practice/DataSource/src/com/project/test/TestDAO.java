package com.project.test;

import com.project.dao.UserDAO;
import com.project.domain.User;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

public class TestDAO {
    @Test
    public void test1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //1. 获取user类对应的Class对象
        Class cls = Class.forName("com.project.domain.User");
        System.out.println(cls);//显示cls对象,是哪个类的Class对象
        System.out.println(cls.getClass()); //输出cls运行类型

        //得到包名
        System.out.println(cls.getPackage());
        System.out.println(cls.getPackage().getName());
        //得到全类名
        System.out.println(cls.getName());
        System.out.println(cls.getSimpleName());

        //通过cls创建对象
        User user = (User) cls.newInstance();

        //获取属性
        Field name = cls.getField("name");
//        System.out.println(name);
        name.set(user,"zhangsan");
        System.out.println(user.getName());



    }




    @Test
    public void testUserDAO(){
        //测试UserDAO 对 表user的操作

        //查询多行记录
        UserDAO userDAO = new UserDAO();
        String sql = "select * from user where id > ?";
        List<User> users = userDAO.queryMulti(sql, User.class, 1);

        System.out.println("输出查询结果集");
        for(User user : users){
            System.out.println(user);
        }

        //查询单行记录
        sql = "select * from user where id = ?";
        User user = userDAO.querySingle(sql,User.class,4);
        System.out.println("单个查询结果:");
        System.out.println(user);

        //3. 查询单行单列
        sql = "select name from user where id = ?";
        Object object = userDAO.queryScalar(sql,5);
        System.out.println("查询结果:");
        System.out.println(object);

        //4. DML操作 insert  update   delete
        sql = "insert into user values(8,'张三丰','男','武当山',?,?)";
        int update = userDAO.update(sql,88,88);
        System.out.println(update>0 ? "成功插入" +update+"条语句":"执行失败");

    }
}


