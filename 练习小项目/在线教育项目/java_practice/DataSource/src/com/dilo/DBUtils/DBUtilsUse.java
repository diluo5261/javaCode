package com.dilo.DBUtils;

import com.dilo.domain.User;
import com.dilo.druid.JDBCUtilsByDruid;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBUtilsUse {

    //使用apach工具类,+ druid完成对表的操作

    /**
     * 查询多个结果,将结果封装到list当中
     */
    @Test
    public void testQueryMany() throws SQLException {
        //1. 获取连接
        Connection conn = JDBCUtilsByDruid.getConnection();

        //2. 使用DBUtils类和接口,先引入DBUTils相关的jar包
        QueryRunner queryRunner = new QueryRunner();

        //3. 执行相关的方法,返回List
        String sql = "select * from user";

        List<User> list = queryRunner.query(conn,sql,new BeanListHandler<>(User.class));
        System.out.println("输出集合信息:");
        for (User user : list){
            System.out.println(user);
        }

        //关闭连接
        JDBCUtilsByDruid.close(null,null,conn);
    }

    @Test
    public void testQuerySingle() throws SQLException {
        //1. 得到连接
        Connection conn = JDBCUtilsByDruid.getConnection();

        //2. 使用DBUtils类和接口,先引入DBUTils相关的jar包
        QueryRunner queryRunner = new QueryRunner();

        //3. 指向sql语句
        String sql = "select * from user where id = ?";

        User user = queryRunner.query(conn,sql,new BeanHandler<>(User.class),2);

        System.out.println(user);

        //释放资源
        JDBCUtilsByDruid.close(null,null,conn);
    }

    //查询结果是单行单列时
    @Test
    public void testScalar() throws SQLException {
        //1. 获取连接
        Connection conn = JDBCUtilsByDruid.getConnection();

        //2.使用DBUtils类和接口
        QueryRunner queryRunner = new QueryRunner();

        //3. 执行sql语句
        String sql = "select name from user where id = ?";

        Object query = queryRunner.query(conn, sql, new ScalarHandler<>(), 2);
        System.out.println(query);

        JDBCUtilsByDruid.close(null,null,conn);
    }

    //演示实现增删查改
    @Test
    public void testDML() throws SQLException {
        //1. 获取连接
        Connection conn = JDBCUtilsByDruid.getConnection();

        //2. 使用DBUtils类和接口
        QueryRunner queryRunner = new QueryRunner();

        //2. 执行sql语句
        //插入
        /*String sql_insert = "insert into user values(7,'5','女','地址5','55','55')";

        int ret = queryRunner.update(conn,sql_insert);
        System.out.println("成功插入"+ret+"条");*/

        //删除
        /*String sql_delete = "delete from user where id = ?";
        int ret_del = queryRunner.update(conn,sql_delete,6);
        System.out.println("成功删除"+ret_del+"条");*/

        //修改
        String sql_update = "update user set name = 'haha' where id = ?";
        int ret_update = queryRunner.update(conn,sql_update,5);
        System.out.println("成功修改" + ret_update+"条");



//方法一:
//        JDBCUtilsByDruid.close(null,null,conn);

        //方法二:使用DBUtils提供的方法关闭
        DbUtils.close(conn);
    }
}
