package com.dilo.dao.dao;

import com.dilo.dao.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 是其他DAO的父类
 */

//泛型指定具体的类
public class BasicDAO<T> {
    private QueryRunner qr = new QueryRunner();

    //开发通用的dml方法,针对任意的表
    public int update(String sql, Object... parameters) throws SQLException {
        Connection conn = null;
        conn = JDBCUtilsByDruid.getConnection();

        int update = qr.update(conn,sql,parameters);

        JDBCUtilsByDruid.close(null,null,conn);
        return update;
    }

    //返回多个对象(查询的结果是多行),针对任意表

    /**
     *
     * @param sql sql语句
     * @param clazz  传入一个类的Class对象,比如Actor.class
     * @param parameters
     * @return
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters) {
        Connection conn = null;

        try {
            conn = JDBCUtilsByDruid.getConnection();

            return qr.query(conn,sql,new BeanListHandler<T>(clazz),parameters);


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtilsByDruid.close(null, null, conn);
        }

    }

    //查询单行结果的通用方法
    public T querySingle(String sql,Class<T> clazz,Object... parameters){
        Connection conn = null;

        try {
            conn = JDBCUtilsByDruid.getConnection();

            return qr.query(conn,sql,new BeanHandler<T>(clazz),parameters);


        } catch (SQLException e) {
            throw  new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, null, conn);
        }
    }


    //返回单行单列得芳芳
    public Object queryScalar(String sql,Object... parameters){
        Connection conn = null;

        try {
            conn = JDBCUtilsByDruid.getConnection();

            return qr.query(conn,sql,new ScalarHandler(),parameters);


        } catch (SQLException e) {
            throw  new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null, null, conn);
        }
    }


}
