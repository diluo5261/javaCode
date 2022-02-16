package jdbc_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *sql脚本：
 *  deop table if exists t_act;
 *  creat table t_act(
 *      actno int,
 *      balance double(7,2) //注意：7表示有效数字的个数，2表示小数位的个数
 *
 *  )；
 *  insert into t_act values(111,20000);
 *  insert into t_act values(222,0);
 *  commit;
 *  select * from t_act;
 *
 *  重点三行代码：
 *      conn.setAutoCommit(false);
 *      conn.commit();
 *      conn.rollback();
 */


public class JDBCTest09 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306","user","root");
            //将自动提交机制改为手动
            conn.setAutoCommit(false);      //开启事务

            //3、获取预编译数据库操作对象
            String sql = "update t_act set balance = ? where actno = ?";
            ps = conn.prepareStatement(sql);

            //给？传值
            ps.setDouble(1,10000);
            ps.setInt(2,111);
            int count = ps.executeUpdate();

            ps.setDouble(1,10000);
            ps.setInt(2,222);
            count += ps.executeUpdate();

            System.out.println(count == 2 ? "转账成功" :"转账失败");

            //程序执行到这里说明以上程序没有异常，事务结束，手动提交数据
            conn.commit();

            //4、执行sql语句

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            //回滚事务
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            //6、释放资源
            try {
                if (ps != null){
                    ps.close();
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
}
