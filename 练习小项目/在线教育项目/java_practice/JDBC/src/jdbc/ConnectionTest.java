package jdbc;

/*
2\如下代码来测试:获取masql数据库连接
想要获取连接:准备工作
- 1、mysql服务开启
- 2、连接的基本条件：指明数据库的ip地址、端口号、用户名、密码
- 3、导入mysql的驱动（即为jdbc中相关接口的实现类的集合）  在idea建立lib包，将炸包复制到lib包内，右键炸包，add as

3、网络编程中的url：代表着互联网中的某一资源的地址
http://192.168.21.34 : 8080 / gmall /index.jsp?user=tom
协议      ip地址        端口          参数列表

 */

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {

    /*
    获取数据库连接方式一：调用DriverManager

     */
    @Test
    public void test1() throws SQLException {

        //提供mysql中driver接口的实现类
        Driver driver = new com.mysql.jdbc.Driver() ;
        //注册驱动
        DriverManager.registerDriver(driver);

        String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT"; //test：表示具体的数据库名
        String user = "root";
        String password = "root";
        //获取连接
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);

    }

    /*
    获取数据库连接方式二：使用反射实现Driver的实例化
    面向接口编程，最好不要出现第三的的API，使程序具备更好的移植性
     */
    @Test
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        //提供mysql中driver接口的实现类的对象
        String className = "com.mysql.jdbc.Driver";
        Class clazz = Class.forName(className);
        Driver driver = (Driver)clazz.newInstance();
        //注册驱动
        DriverManager.registerDriver(driver);

        String url = "jdbc:mysql://localhost:3306/test"; //test：表示具体的数据库名
        String user = "root";
        String password = "root";
        //获取连接
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
    }

    /*

    获取编数据库的连接方式三：省略注册的过程
     */

    @Test
    public void test3() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        //1、获取连接的4个基本信息
        String className = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test"; //test：表示具体的数据库名
        String user = "root";
        String password = "root";

        //2、加载驱动
        Class.forName(className);

        //注册驱动
       // DriverManager.registerDriver(driver);
        /*
        之所以不在代码中显示使用DriverManager的注册功能了，是因为在mysql的Driver类的源码中
        发现：
        static {
                try {
                    DriverManager.registerDriver(new Driver());
                } catch (SQLException var1) {
                    throw new RuntimeException("Can't register driver!");
                }
            }


         */

        //获取连接
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
    }

    /*

    连接方式四：将数据库连接的基本信息声明在配置文件中
    使用配置文件的好处
        1、实现了数据和代码的分离，解耦。
        2、编写好的java程序部署到服务器上是，需要打包。如果代码修改过，就需要重新打包
        使用配置文件的方式，如果配置信息修改，并没有导致代码的子u该。所以不需要重写打包
     */
    @Test
    public void test4() throws IOException, ClassNotFoundException, SQLException {
        //1.加载配置文件
        //加载资源默的路径默认为src下
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        //读取配型文件中的4各进本信息
        Properties pros = new Properties();
        pros.load(is);

        //2.读取配置信息
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //3.加载驱动
        Class.forName(driverClass);

        //4.获取连接
        Connection conn = DriverManager.getConnection(url,user,password);
        System.out.println(conn);


    }

}
