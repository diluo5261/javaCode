package JDBC;

import com.mysql.cj.protocol.Resultset;
import org.junit.Test;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 功能实现：
 * 1、需求：模拟用户登录功能实现
 * 2、业务描述
 *      程序运行的时候，提供一个输入的入口，可以让用户输入用户名和密码，用户输入用户名和密码之后，提交信息，java程序收集到用户信息
 *      java程序连接数据库验证用户名和密码是否合法
 *
 *
 *
 */

public class JDBCTest2 {
    public static void main(String[] args) {
        //初始化登录界面
        Map<String,String> loginInfo = loginUI();

        //判断登录是否成功
        boolean loginSuccess = login(loginInfo);
        System.out.println(loginSuccess?"登录成功":"登录失败");
    }

    private static boolean login(Map<String, String> loginInfo) {
        boolean loginSuccess = false;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        try{

            //1、注册驱动

            //2、获取连接
            conn = DriverManager.getConnection(url,user,password);

            //3、获取数据库操作对象
            stmt = conn.createStatement();

            //4、执行sql语句
            String sql = "select * from user_info where loginName = '"+loginInfo.get("loginName")+"' " +
                    "and loginPassword ='"+loginInfo.get("loginPwd")+"'";

            rs = stmt.executeQuery(sql);

            if(rs.next()){
                loginSuccess = true;

            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{

            //6、释放资源
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
        return loginSuccess;
    }

    /**
     * 初始化登录界面
     * @return  返回用户输入的登录信息
     */
    private static Map<String, String> loginUI() {
        Scanner scan = new Scanner(System.in);
        System.out.print("输入账号：");
        String loginName = scan.nextLine();

        System.out.print("输入密码：");
        String loginPwd = scan.nextLine();

        Map<String,String> loginInfo = new HashMap<>();
        loginInfo.put("loginName",loginName);
        loginInfo.put("loginPwd",loginPwd);

        return loginInfo;
    }

}
