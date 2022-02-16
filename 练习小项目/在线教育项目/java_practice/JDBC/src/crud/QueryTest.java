package crud;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import util.JDBCUtil;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/*
数据表的查询操作
 */
public class QueryTest {

    /*
    查询表中一条记录，
     */
    @Test
    public void test1() {
        Connection connection = null;
        try {
            //1、获取数据库的连接
            connection = JDBCUtil.getConnection();

            //2、提供一条占位符的查询语句
            String sql = "select id,name,email,birth from customers where id = ?";
            //3、创建QueryRunner实例
            QueryRunner runner = new QueryRunner();

            //4、通过QueryRunner的实例，调用其Query（）
            BeanHandler<Customer>  handler = new BeanHandler<>(Customer.class);
            Customer customer = runner.query(connection,sql,handler,1);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、关闭资源
            JDBCUtil.close(connection);
        }
    }


    /*
    * 查询表中的多条记录
    * */
    @Test
    public void test2() {
        Connection connection = null;
        try {
            //1、获取数据库的连接
            connection = JDBCUtil.getConnection();

            //2、提供一条占位符的查询语句
            String sql = "select id,name,email,birth from customers where id > ?";
            //3、创建QueryRunner实例
            QueryRunner runner = new QueryRunner();

            //4、通过QueryRunner的实例，调用其Query（）
            BeanListHandler<Customer>  handler = new BeanHandler<>(Customer.class);
            List<Customer> query = runner.query(connection, sql, handler, 1);

            query.forEach(System.out :: println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、关闭资源
            JDBCUtil.close(connection);
        }
    }

    /*
   MapHandler:对应查询表中的一条记录，以map对象的方式返回。
   map中key为表中的字段名，map中value为表中一条数据的数据值
    */
    @Test
    public void test3() {
        Connection connection = null;
        try {
            //1、获取数据库的连接
            connection = JDBCUtil.getConnection();

            //2、提供一条占位符的查询语句
            String sql = "select id,name,email,birth from customers where id = ?";
            //3、创建QueryRunner实例
            QueryRunner runner = new QueryRunner();

            //4、通过QueryRunner的实例，调用其Query（）
            MapHandler handler = new MapHandler();
            Map<String, Object> query = runner.query(connection, sql, handler, 1);
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、关闭资源
            JDBCUtil.close(connection);
        }
    }

    /*
    ScalarHandler:用于查询表中的特殊值,比如count（*），max.min
     */
    @Test
    public void test5(){
        Connection connection = null;
        try {
            //1、获取数据库的连接
            connection = JDBCUtil.getConnection();

            //2、提供一条占位符的查询语句
            String sql = "select count(*) from customers ";
            //3、创建QueryRunner实例
            QueryRunner runner = new QueryRunner();

            //4、通过QueryRunner的实例，调用其Query（）
            ScalarHandler<Customer> handler = new ScalarHandler<>();

             long count = runner.query(connection,sql,handler);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、关闭资源
            JDBCUtil.close(connection);
        }


    }
}


