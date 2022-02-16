package jdbc_01;

import DBUtil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * 两个任务：
 *      第一：测试DBUtili是否好用
 *      第二：模糊查询怎么写
 *
 */
public class JDBCTest10 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String sql = "select ename from emp where ename like?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,"_a%");

            rs = ps.executeQuery();

            while(rs.next()){
                System.out.println(rs.getString("ename"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
    }
}
