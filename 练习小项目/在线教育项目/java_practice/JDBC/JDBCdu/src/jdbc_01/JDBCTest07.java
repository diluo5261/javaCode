package jdbc_01;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/*
    1、解决SQL注入问题
    只要用户提供的信息不参与SQL语句的编译过程，问题就解决了
    即使用具提供的信息中含有SQL语句的关键字，但是没有参与编译，不起作用
    要想用具信息不参与编译sql语句的编译，就必须使用java.sql.PreparedStatement
    PreparedStatement接口继承了java.sql.Statement
    PreparedStatement是属于预编译的数据库操作对象
    PreparedStatement的原理是：预先对sql语句的框架进行编译，然后再给sql语句传“值”

    2、解决sql注入的关键是什么
        用户提供的信息中即使含有sql语句关键字，但是这些关键字并没有参与编译，不起作用

    3、对比一下Statement和PreparedStatement？
        - Statement存在sql注入问题，PreparedStatement解决了sql注入问题
        - Statement编译一次执行一次，PreparedStatement编译一次执行n次，PreparedStatement效率更高一点
        - PreparedStatement会在编译截断做类型的安全检查
     4、什么情况下必须使用Statement
        业务方面要求支持sql注入的时候。
 */
public class JDBCTest07 {

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
        PreparedStatement  ps = null;   //这里使用PreparedStatement预编译的数据库操作对象****************************
        ResultSet rs = null;
        try {

            //1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");

            //3、获取预编译的数据库操作对象
            String sql = "select * from t_user where loginName = ? and loginPed = ?";//sql语句的框架。一个？代表一个占位符，将来接受一个值，占位符不能用单引号括起来

            //程序执行到此处，会发送sql语句框子给DBMS，然后DBMS进行sql以语句的预编译
            ps = conn.prepareStatement(sql);//*******************************

            //给占位符?传值（第一个问好下标是1，第二个问好下标是2，JDBC中所有下标从1开始）
            ps.setString(1,userLoginInfo.get("loginName"));
            ps.setString(2,userLoginInfo.get("loginPwd"));


            //4、执行sql语句
//            String sql = "select * from t_user where loginName = '"+loginName+"' and loginPwd = '"+loginPwd+"'";

            rs = ps.executeQuery();
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
