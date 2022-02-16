package crud;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import util.JDBCUtil;

import java.sql.Connection;

/*
测试向数据表中添加、删除和修改数据

 */
public class UpdateTest {
    //像数据表中添加一条记录
    @Test
    public void test1()  {
        Connection conn = null;
       try{
           //1、获取数据库连接
           conn = JDBCUtil.getConnection();
           //2、提供一个添加操作的sql
           String sql = "insert into customer(name,email,birth) values('Tom','tom@126.com','2020-9-8')";

           //3、使用提供好得多QueryRunner
           QueryRunner runner = new QueryRunner();
           int count = runner.update(conn,sql);
           System.out.println("添加了" + count + "条记录");
       }catch(Exception e){
           e.printStackTrace();
       }finally{
            //4、资源的关闭
            JDBCUtil.close(conn);
       }
    }


    //方式二:推荐使用占位符的方式
    @Test
    public void test2()  {
        Connection conn = null;
        try{
            //1、获取数据库连接
            conn = JDBCUtil.getConnection();
            //2、提供一个包含占位符的sql
            String sql = "insert into customer(name,email,birth) values(?,?,?)";

            //3、使用提供好得多QueryRunner
            QueryRunner runner = new QueryRunner();
            int count = runner.update(conn,sql,"Tom","tom@126.com","2020-09-08");
            System.out.println("添加了" + count + "条记录");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //4、资源的关闭
            JDBCUtil.close(conn);
        }
    }


    //删除记录操作
    @Test
    public void test3(){

        Connection conn = null;
        try{
            //1、获取数据库连接
            conn = JDBCUtil.getConnection();
            //2、提供一个添加操作的sql
            String sql = "delete from customers where id > ?";

            //3、使用提供好得多QueryRunner,调用update()方法,上西安数据的插入
            QueryRunner runner = new QueryRunner();
            int count = runner.update(conn,sql,19);
            System.out.println("删除了" + count + "条记录");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //4、资源的关闭
            JDBCUtil.close(conn);
        }
    }

    //修改表的数据
    @Test
    public void test4(){
        Connection conn = null;
        try{
            //1、获取数据库连接
            conn = JDBCUtil.getConnection();
            //2、提供一个添加操作的sql
            String sql = "update customer set email = ? where id = ?";

            //3、使用提供好得多QueryRunner,调用update()方法,上西安数据的插入
            QueryRunner runner = new QueryRunner();
            int count = runner.update(conn,sql,"tmo@gmail.com",19);
            System.out.println("修改了" + count + "条记录");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //4、资源的关闭
            JDBCUtil.close(conn);
        }


    }
}
