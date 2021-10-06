package com.dilo.dao;

import com.dilo.domainn.Admin;
import com.dilo.domainn.NormalUser;
import com.dilo.domainn.User;
import com.dilo.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    //主要实现根据用户名找密码这样的逻辑即可
    public User selectByName(String name){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //获取连接
            conn = DBUtil.getConnection();

            //执行sql语句,根据用户名查询对那个的密码
            String sql = "select * from user where username = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1,name);
            resultSet = statement.executeQuery();

            if (resultSet.next()){
                //根据当前 User 是否是管理员,决定返回一个 Admin 还NormalUser

                User user = null;
                if (resultSet.getInt("isAdmin") == 1){
                    user = new Admin();
                }else{
                    user = new NormalUser();
                }

                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;

            }else{
                System.out.println("用户名" + name+"不存在!");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,statement,resultSet);
        }

        return null;
    }

}
