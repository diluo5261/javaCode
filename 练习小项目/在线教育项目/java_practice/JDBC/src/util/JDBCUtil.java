package util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {

    //这种方式每次调用，就会生成一个连接池，效率差
   /* public static Connection getConnection() throws Exception {
        //
        Properties pros = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        pros.load(is);

        //通过DruidDataSourceFactory创建一个数据源
        DataSource dataSource = DruidDataSourceFactory.createDataSource(pros);

        //通过数据源获取连接
        Connection connection = dataSource.getConnection();
        return connection;

    }*/

    /*
    改进后
     */
    public static Connection getConnection() throws Exception {



        //通过数据源获取连接
        Connection connection = dataSource.getConnection();
        return connection;

    }

    private static DataSource dataSource;

    static{
        //提供Properties，并加载指定配置文件流
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);
            //通过DruidDataSourceFactory创建一个数据源
            dataSource = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //关闭连接
    public static void close(Connection connection){
        if (connection != null){

            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

}
