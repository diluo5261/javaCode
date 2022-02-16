package Druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/*
一、使用数据库连接池技术的好处
1、更方便的过去连接对象，效率高
2、资源可以更好的重复利用
3、便于进行必要的管理

二、常见的数据库连接池技术？DBCP：速度快，不稳定；C3P0：稳定、速度慢；Druid：间距两者的优点

三、DataSource 通常被称为数据源，它包含连接池和连接池管理两个部分，习惯上也经常把DataSource称为连接池

 */
public class DruidTest {

    /*
    方法一：
    使用Druid数据库连接池获取数据库的信息
     */
    @Test
    public void test1() throws SQLException {
        //创建池
        DruidDataSource source = new DruidDataSource();
        source.setUsername("root");
        source.setPassword("root");
        source.setUrl("jdbc:mysql://localhost:3306/test");
        source.setDriverClassName("com.mysql.jdbc.Driver");

        //设置同时连接信息
        source.setMaxActive(10);
        source.setInitialSize(5);

        //获取连接池中的一个数据库连接
        DruidPooledConnection connection = source.getConnection();

    }

    /*
    方法二：
    将数据库连接的基本信息放到而配置文件中
     */
    @Test
    public void test2() throws Exception {
        //提供Properties。并加载指定配置文件的流
        Properties pros = new Properties();

        //加载资源的路径默认为：src下
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");

        pros.load(is);
        //通过Druid创建一个数据源
        DataSource dataSource = DruidDataSourceFactory.createDataSource(pros);

        System.out.println(dataSource.getConnection());


    }

}

