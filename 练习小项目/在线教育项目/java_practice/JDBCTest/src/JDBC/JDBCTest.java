package JDBC;

import org.junit.Test;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest {
    @Test
    public void test(){
        Connection conn = null;
        Statement stm = null;
        try{
            //1、注册驱动
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            //2、获取连接
            String url = "jdbc:mysql://localhost:3306/test1";
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("成功连接"+conn+"数据库！");

            //3、获取数据库操作对象
            stm = conn.createStatement();

            //4、执行sql语句
            String sql = "insert into students values(null,'猪八戒',26)";
            int count = stm.executeUpdate(sql);

            System.out.println(count == 1?"插入数据成功" :"插入数据失败");



        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(stm != null){
                    stm.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    @Test
    public void test2(){
        Connection conn = null;
        Statement stm = null;

        try{
            //1、注册驱动
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1","root","root");

            //3、获取数据库操作对象
            stm = conn.createStatement();

            //4、执行sql语句
            String sql = "insert into students values(null,'孙悟空',30)";
            int count = stm.executeUpdate(sql);
            System.out.println(count == 1? "插入成功" : "插入失败");

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //释放资源
            try {
                if(stm != null){
                    stm.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Test
    public void test3(){
        Connection conn = null;
        Statement stm = null;
        try{
            //1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1","root","root");
            System.out.println("成功获取" +conn +"数据库连接");

            //3、获取数据库的操作对象
            stm = conn.createStatement();

            //4、实行sql语句
            String sql = "insert into students values(null,'白龙马',56)";
            boolean flag = stm.execute(sql);
            System.out.println(flag);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //6、释放资源
            try {
                if(stm != null){
                    stm.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //JDBC完成delete
    @Test
    public void test4(){
        Connection conn = null;
        Statement stm = null;
        try{
            //1、注册驱动

            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1","root","root");

            //3、获取数据库操作对象
            stm = conn.createStatement();

            //4、执行sql语句
            String sql = "delete from students where name = '猪八戒'";
            int count = stm.executeUpdate(sql);
            System.out.println(count == 1?"删除成功":"删除失败");

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(stm != null){
                    stm.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Test
    public void test5(){
        //使用资源绑定器
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        Statement stm = null;
        try{
            //注册驱动

            //2、获取连接
            conn = DriverManager.getConnection(url,user,password);
            System.out.println(conn == null ?"连接失败":"连接成功");

            //3、获取数据库操作对象
            stm = conn.createStatement();

            //4、执行sql语句
            String sql = "update students set name = '天下第一',age = '99' where id = 3 ";
            int count = stm.executeUpdate(sql);
            System.out.println(count);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //6、释放资源
            try {
                if(stm != null){
                    stm.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //处理查询结果
    @Test
    public void test6(){
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;

        try{
            //2、获取连接对象
            conn = DriverManager.getConnection(url,user,password);
            //3、获取数据库操作对象
            stm = conn.createStatement();

            //4、执行sql语句
            String sql = "select * from students";
            rs = stm.executeQuery(sql);

            while(rs.next()){
               /* String id = rs.getString(1);
                String name = rs.getString(2);
                String age = rs.getString(3);*/

                String id = rs.getString("id");
                String name = rs.getString("name");
                String age = rs.getString("age");




                System.out.println(id + name + age);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{

            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(stm != null){
                    stm.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Test
    public void test7(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        try{
            //1、注册驱动

            //2、获取连接
            conn = DriverManager.getConnection(url,user,password);
//            System.out.println(111111111);

            //3、获取预编译的数据库操作队形
            String sql = "select * from user_info where loginName = ? and loginPassword = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"tom");
            ps.setString(2,"tom123");

            //4、执行sql语句
            rs = ps.executeQuery();

            while(rs.next()){
                String loginName = rs.getString("loginName");
                String loginPassword = rs.getString("loginPassword");
                System.out.println(loginName + loginPassword);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{

            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(ps != null){
                    ps.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }





}
