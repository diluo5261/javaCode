package JDBC;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JDBCTest3 {
    public static void main(String[] args) {
        //1、界面初始化
        Map<String,String> loginInfo = initUI();

        //2、验证用户名和密码
        boolean loginSuccess = login(loginInfo);
        System.out.println(loginSuccess?"登录成功":"登录失败");
    }

    /**
     * 改进后。，不存在sql注入的问题
     * @param loginInfo
     * @return
     */
    private static boolean login(Map<String, String> loginInfo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        boolean loginSuccess = false;

        try{
            //1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2、获取连接
            conn = DriverManager.getConnection(url,user,password);

            //3、获取预编译数据操作对象
            String sql = "select * from user_info where loginName = ? and loginPassword = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,loginInfo.get("loginName"));
            ps.setString(2,loginInfo.get("loginPwd"));

            //4、执行sql语句
            rs = ps.executeQuery();

            if(rs.next()){
                loginSuccess = true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (rs != null) rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if (ps != null) ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if (conn != null) conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return loginSuccess;
    }

    /*

    修改前，存在sql注入的问题
     */
    private static boolean login1(Map<String, String> loginInfo) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        boolean loginSuccess = false;

        try{
            //1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2、获取连接
            conn = DriverManager.getConnection(url,user,password);

            //3、获取数据库操作对象
            stmt = conn.createStatement();

            //4、执行sql语句
            String sql = "select * from user_info where loginName = '"+loginInfo.get("loginName")+"' and loginPassword ='"+loginInfo.get("loginPwd")+"'";

            //5、处理查询结果集
            rs = stmt.executeQuery(sql);

            if(rs.next()){
                loginSuccess = true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(rs != null) rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(stmt != null) stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(conn != null) conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return loginSuccess;
    }

    /**
     * 用户输入账号和密码
     * @return 返回用户输入的账号和密码
     */
    private static Map<String, String> initUI() {
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入账号：");
        String loginName = scan.nextLine();
        System.out.print("请输入密码：");
        String loginPwd = scan.nextLine();

        Map<String,String> loginInfo = new HashMap<>();
        loginInfo.put("loginName",loginName);
        loginInfo.put("loginPwd",loginPwd);
        return loginInfo;
    }
}
