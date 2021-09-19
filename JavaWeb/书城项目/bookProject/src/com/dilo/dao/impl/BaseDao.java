package com.dilo.dao.impl;

import com.dilo.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    //使用DButils操作数据库
    private QueryRunner runner = new QueryRunner();

    /**
     * update ()方法用来执行,insert update  delete语句
     * @return 返回-1 ,说明执行失败,返回其他表示影响的行数
     */
    public int update(String sql,Object... args)  {
        Connection conn = null;
        try {
        conn = JDBCUtils.getConnection();
            return runner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            JDBCUtils.close(conn);
        }
        return -1;
    }


    /**
     * 查询返回一个javaBean的sql语句
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object... args){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            return runner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn !=null){
                JDBCUtils.close(conn);
            }
        }
        return null;
    }


    /**
     * 查询返回多个javaBean语句
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            return runner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn !=null){
                JDBCUtils.close(conn);
            }
        }
        return null;
    }

    public Object queryForSingleValue(String sql,Object... args){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            return runner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if (conn != null){
                JDBCUtils.close(conn);
            }
        }
        return null;
    }


}
