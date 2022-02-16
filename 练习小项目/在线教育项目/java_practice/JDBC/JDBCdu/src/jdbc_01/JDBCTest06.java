package jdbc_01;


import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
功能实现：
1、需求：模拟用户登录功能实现
2、业务描述：
    程序运行的时候，提供一个输入的入口，可以让用户输入用户名和密码，用具输入用户名和密码之后，提交信息，java程序收集到用具信息
    java程序连接数据库验证用户名和密码是否合法

    合法：显示登录成功
    不合法：显示登录失败

3、数据的准备
    实际开发中，表的涉及会使用专业的建模工具，我们这里安装一个工具：PowerDesigner
    使用PD工具来进行数据库表的涉及

4、当前程序存在的问题：
    用户名：fdsa
    密码：fdsa' or '1'='1
    登录成功
    这种现象被称为SQL注入（安全隐患）

5、导致SQL注入的根本原因是什么
    用具输入的信息中含有sql语句的关键字，并且这些关键字参与sql语句的编译过程
    导致语句的原意被扭曲，进而达到sql注入

 */
public class JDBCTest06 {
    public static void main(String[] args) {
        //初始化一个界面
        Map<String,String> userLoginInfo = initUI();
        //验证用户名和密码

        boolean loginSuccess = login(userLoginInfo);

        //最后输出结果
        System.out.println(loginSuccess ? "登录成功" :"登录失败");
    }

    /**
     * 用户登录
     * @param userLoginInfo 用户登录信息
     * @return false表示失败，true表示成功
     */
    private static boolean login(Map<String, String> userLoginInfo) {

        //打标记
        boolean loginSuccess = false;
        //JDBC代码
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {

            //1、注册驱动
                Class.forName("com.mysql.cj.jdbc.Driver");

            //2、获取连接
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");

            //3、获取数据库操作对象
            stmt = conn.createStatement();

            //4、执行sql语句
//            String sql = "select * from t_user where loginName = '"+loginName+"' and loginPwd = '"+loginPwd+"'";
            String sql = "select * from t_user where loginName = '"+ userLoginInfo.get("loginName")+"' and loginPed = '"+
                    userLoginInfo.get("loginPwd")+"'";
            rs = stmt.executeQuery(sql);
            //5、整理查询结果集

            while(rs.next()){
                //有数据说明标定成功
                loginSuccess = true;

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //6、释放资源
        }
        return loginSuccess;
    }

    /**
     * 初始化用具界面
     * @return 用户输入的用户名和密码等登录信息
     */
    private static Map<String, String> initUI() {
        Scanner scan = new Scanner(System.in);
        System.out.println("用户名");
        String loginName = scan.nextLine();
        System.out.println("密码：");
        String loginPwd = scan.nextLine();

        Map<String,String> userLogInfo = new HashMap<>();
        userLogInfo.put("loginName",loginName);
        userLogInfo.put("loginPed",loginPwd);

        return userLogInfo;

    }


}
