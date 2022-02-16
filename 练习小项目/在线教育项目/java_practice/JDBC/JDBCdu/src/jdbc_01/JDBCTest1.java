package jdbc_01;

import org.junit.Test;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest1 {
    @Test
    public void test(){


        Connection conn = null;
        Statement statement = null;
        try {
            //1、注册驱动
            Driver driver = new com.mysql.cj.jdbc.Driver(); //多态，父类型引用子类型对象
            DriverManager.registerDriver(driver);

            //2、获取连接

            /*
            url：同意资源定位符（网络中某个资源的绝对路径）

            url：包含那几个部分
            协议
            IP
            PORT
            资源名

            http://192.61.200.7:80/index.heml
            http://  通信协议
            182.61.200.7  服务器IP地址
            index.html 服务器上某个资源名

            jdbc:mysql://localhost:3306/test

            jdbc:mysql:// 协议
            127.0.0.1  IP地址
            3306  mysql数据库端口号
            test 具体的数据库实例名


            说明：localhost 和127.0.0.1 都是本机地址

            什么是通信协议，有什么用：
                通信协议是通信之前就提前定好的数据格式。
                数据包具体怎么传输数据，格式提前订好
             */

            String url = "jdbc:mysql://localhost:3306/test";
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("数据连接" + url+"成功！");


            //3、获取数据库操作对象(statement 专门执行sql语句)
            statement = conn.createStatement();

            //4、执行sql语句
            //JDBC中的语句不需要写分号，写上会报错
            String sql = "insert into customers(name,email,birth) values('tom','tom@126.com','2020-08-08')";
            //专门执行DML语句的（insert delete update）
            //返回值是”影响数据库中的记录条数“
            int count = statement.executeUpdate(sql);
            System.out.println(count);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6、释放资源
            //为了保证资源一定释放，在finally语句块中关闭资源
            //并且遵循从小到达依次关闭，分别对其进行try catch
            try {
                if(statement != null){
                    statement.close();
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
        //5、处理查询结果集
    }

    //JDBC完成delete
    @Test
    public void test2(){

        Connection conn = null;
        Statement stmt = null;
        try {
            //1、注册驱动

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            //2、获取连接
            String url = "jdbc:mysql://localhost:3306/test";
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection(url,user,password);
            //3、获取数据库操作对象
            stmt = conn.createStatement();


            //4、执行sql语句
            String sql = "delete from customers where id = 19";
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1 ? " 删除成功 ": " 删除失败 ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //6、释放资源
            try {
                if(stmt != null){
                    stmt.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    //注册驱动另一种方式（常用）
    @Test
    public void test3() throws ClassNotFoundException, SQLException {
        //因为参数是一个字符串，字符串可以写到xxx.properties文件中
        //以下方法不需要接受返回值，因为我们指向用它的类的加载器
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2、获取连接
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);
    }



    //将数据库的所有信息配置到配置文件中
    /*
    实际开发中不建议把连接数据库的信息写死到java程序中

     */

    @Test
    public void test4(){

        //使用资源绑定器绑定属性配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        Statement stmt = null;
        try {
            //1、注册驱动

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            //2、获取连接
            conn = DriverManager.getConnection(url,user,password);
            //3、获取数据库操作对象
            stmt = conn.createStatement();


            //4、执行sql语句
            String sql = "delete from customers where id = 19";
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1 ? " 删除成功 ": " 删除失败 ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //6、释放资源
            try {
                if(stmt != null){
                    stmt.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    //处理查询结果集
    @Test
    public void test5(){
        Connection conn= null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");

            //3、获取数据库操作对象
            stmt = conn.createStatement();

            //4、执行sql
            String sql = "select * from customers";
            //int executeUpdate(insert / delete / update)
            //ResultSet executeQuery(select);
            rs = stmt.executeQuery(sql);  //专门执行DQL语句的方法

            //5、处理查询结果集


            while(rs.next()) {

                //光标指向的行有数据
                //取数据
                //getString()方法的特点是：不管数据库中是什么类型，都已String的形式取出
                //jdbc中每列从1开始,123说的是第几列

              /*  String name = rs.getString(1);
                String email = rs.getString(2);
                String birth = rs.getString(3);*/

                //建议写列名，这样比较健壮，查询顺序更改后，数据不会改
                //这个不是以列的下标获取，查询语句的列的名字，列名称不是表中的列名称，是查询结果的列名称
                String name = rs.getString("name");
                String email = rs.getString("email");
                String birth = rs.getString("birth");

                //除了可以以String类型取出外，还可以以特定类型取出

//                int sal = rs.getInt(birth);
                System.out.println(name + email + birth);
            }



            //6、释放资源
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            try {
                if(stmt != null){
                    stmt.close();
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
