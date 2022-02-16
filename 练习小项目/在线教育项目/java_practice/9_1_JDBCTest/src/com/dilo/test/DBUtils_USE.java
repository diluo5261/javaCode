package com.dilo.test;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBUtils_USE {

    //使用apache_DBUtils  工具类, + druid 完成对表的crud操作

    public void testQueryMany() throws SQLException {
        //1.得到连接
        Connection conn = JDBCUtilsByDruid.getConnection();

        //2.使用DBUtils类和接口,先引入DBUTils 相关的jar包
        QueryRunner queryRunner = new QueryRunner();

        //4. 就可以执行相关的方法,返回ArrayList
        String sql = "select * from actor";
        List<Actor> list = queryRunner.query(conn,sql,new BeanListHandler<>(Actor.class),1);
        //1. query 方法就是执行sql语句,得到resultSet -->封装到-->ArrayList集合中
        //2. 返回集合
        //3. connection : 连接
        //4. sql :执行sql语句
        //5.new BeanListHandler<>( Actor.class) : 在将resultser --> Actor 对象 -->封装到ArrayList
        //底层使用反射机制,区获取Actor类的属性,然后进行封装
        //6. 1就是给sql 语句中的 ? 赋值,可以有多个值,因为是可变参数,Object ... params
        //7. 底层得到的resultset,会在query关闭,关闭parementStatement对象
        System.out.println("输出集合信息");
        for (Actor actor: list){
            System.out.println(actor);
        }

        JDBCUtilsByDruid.close(null,null,conn);
    }

    //演示apache-dbutils + druid
    public void testQuerySingle() throws SQLException {
        //1.得到连接
        Connection conn = JDBCUtilsByDruid.getConnection();

        //2.使用DBUtils类和接口,先引入DBUTils 相关的jar包
        QueryRunner queryRunner = new QueryRunner();

        //4. 就可以执行相关的方法,返回单个对象
        String sql = "select * from actor where id = ?";

        //返回的是单行记录,使用的Hander  是 BeanHandler
        Actor query = queryRunner.query(conn, sql, new BeanHandler<>(Actor.class), 1);

        //释放资源
        JDBCUtilsByDruid.close(null,null,conn);
    }

    //演示apache-dbutils + druid 完成查询结果是单行单列-返回的就是object
    public void testScalar() throws SQLException {
        //1.得到连接
        Connection conn = JDBCUtilsByDruid.getConnection();

        //2.使用DBUtils类和接口,先引入DBUTils 相关的jar包
        QueryRunner queryRunner = new QueryRunner();

        //4. 就可以执行相关的方法,返回单个行单列
        String sql = "select  name from actor where id = ?";
        //因为返回的是一个对象,使用的handler就是 ScalrHandler
        Object query = queryRunner.query(conn, sql, new ScalarHandler<>(), 2);
        System.out.println(query);

        //释放资源
        JDBCUtilsByDruid.close(null,null,conn);
    }

    //演示apache-dbutils + druid 完成dml(update insert delete)
    public void testDML() throws SQLException {

        //1.得到连接
        Connection conn = JDBCUtilsByDruid.getConnection();

        //2.使用DBUtils类和接口,先引入DBUTils 相关的jar包
        QueryRunner queryRunner = new QueryRunner();

        //4. 组织sql
        String sql = "update actor set name =? where id =?";
//        String sql = "insert into actor values(null,'王五','女',?,?)";

        //执行dml操作时queryRunner.update()
        //返回值是受影响的行数

        int affectedRow = queryRunner.update(conn, sql, "李四", 4);


        //释放资源
        JDBCUtilsByDruid.close(null,null,conn);
    }
}
