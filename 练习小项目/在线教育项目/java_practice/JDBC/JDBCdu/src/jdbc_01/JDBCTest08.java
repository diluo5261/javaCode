package jdbc_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Preparement实现增删查改（insert，delete update）
 */
public class JDBCTest08 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取链接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");

            //3、获取预编译的数据库的对象

            String sql = "insert into customer(name,email) values(?,?)";
//            String sql = "update customer set name = ? ,email = ? where id = ? ";修改
//            String sql = "delete from dept where id = ?"; //删除数据
            ps = conn.prepareStatement(sql);
            ps.setString(1,"jone");
            ps.setString(2,"jone@163.com");

            //4、执行sql
            int count = ps.executeUpdate();
            System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
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
