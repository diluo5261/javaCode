package test;

import com.bit.util.JdbcUtils;
import com.bit.util.TimeUtils;
import com.mysql.cj.jdbc.JdbcConnection;
import com.sun.javaws.IconUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {



//    4. 删除id最大的一条借阅记录
    public static void main4(String[] args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            //获取连接
            conn = JdbcUtils.getConnection();
            //执行sql语句

            String s1= "select max(id) from borrow_info";
            String s2 = "select res.max_id from(select max(id) as max_id from borrow_info ) as res";
            String sql = "delete from borrow_info where id = (select res.max_id from(select max(id) as max_id from borrow_info ) as res)";

            preparedStatement = conn.prepareStatement(sql);
            int ret = preparedStatement.executeUpdate();

            if(ret == 1){
                System.out.println("成功删除1条记录");
            }else{
                System.out.println("删除失败");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn,preparedStatement,null);
        }
    }




    //3. 修改图书《深入理解Java虚拟机》的价格为61.20
    public static void main3(String[] args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            //获取连接
            conn = JdbcUtils.getConnection();
            //执行sql语句
            String sql = "update book set price = ? where name =?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setBigDecimal(1,new BigDecimal(61.2));
            preparedStatement.setString(2,"深入理解Java虚拟机");
            int ret = preparedStatement.executeUpdate();

            if(ret == 1){
                System.out.println("成功修改1条记录");
            }else{
                System.out.println("修改失败");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn,preparedStatement,null);
        }
    }

    //2. 查询计算机分类下的图书借阅信息
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //获取连接
            conn = JdbcUtils.getConnection();
            //执行sql语句
            String sql = "select bk.name '书名',bk.author '作者',bk.price '价格',bo.start_time '借阅日期',bo.end_time '归还日期' from book bk join category  c on bk.category_log = c.id join borrow_info bo on bk.id = bo.book_id  join student st on st.id = bo.student_id where c.name = ?";


            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1,"计算机");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                System.out.print(resultSet.getString("书名")+"    ");
                System.out.print(resultSet.getString("作者")+"    ");
                System.out.print(resultSet.getString("价格")+"    ");
                System.out.print(resultSet.getString("借阅日期")+"    ");
                System.out.print(resultSet.getString("归还日期"));
                System.out.println();
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn,preparedStatement,resultSet);
        }
    }



//    新增貂蝉同学的借阅记录：诗经，从2019年9月25日17:50到2019年10月25日17:50
    public static void main1(String[] args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            //获取连接
            conn = JdbcUtils.getConnection();

String sql =
        "insert into borrow_info(book_id, student_id, start_time, end_time) select b.id,s.id,?,? from book b join student s where b.name=? and s.name=?";

            preparedStatement = conn.prepareStatement(sql);

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setTimestamp(1, TimeUtils.getTimestamp("2019-09-25 17:50:00"));
            preparedStatement.setTimestamp(2, TimeUtils.getTimestamp("2019-10-25 17:50:00"));
            preparedStatement.setString(3, "诗经");
            preparedStatement.setString(4, "貂蝉");

            int ret = preparedStatement.executeUpdate();
            System.out.println("成功插入"+ret+"条语句");

            JdbcUtils.close(conn,preparedStatement,null);



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
