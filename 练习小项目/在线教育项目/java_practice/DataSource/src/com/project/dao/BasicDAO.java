package com.project.dao;

import com.project.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 开发BasicDAO,是其他类的父类
 */
public class BasicDAO <T>{//泛型指定具体类型
    private QueryRunner qr = new QueryRunner();

    public int update(String sql,Object... parameters){
        Connection conn= null;
        try {
            conn = JDBCUtilsByDruid.getConnection();
            int update = qr.update(conn, sql, parameters);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilsByDruid.close(null,null,conn);
        }
        return -1;
    }

    //返回多个对象(即查询结果是多行的),针对任意表

    /**
     *
     * @param sql  sql语句,可以有?
     * @param clazz   传入一个类的Class对象,比如Actor.class
     * @param parameters
     * @return
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters){
        Connection conn = null;
        try {
            //1. 获取连接

            conn = JDBCUtilsByDruid.getConnection();
            List<T> list= qr.query(conn,sql,new BeanListHandler<T>(clazz),parameters);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilsByDruid.close(null,null,conn);
        }

        return null;
    }

    //查询单行的通用结果
    public T querySingle(String sql , Class<T> clazz, Object... parameters){

        Connection conn = null;
        try {
            //1. 获取连接
            conn = JDBCUtilsByDruid.getConnection();

            //2. 执行查询语句
            T query = qr.query(conn, sql, new BeanHandler<T>(clazz), parameters);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilsByDruid.close(null,null,conn);
        }

        return null;
    }

    //查询单行单列的方法,即返回单值的方法
    public Object queryScalar(String sql,Object... parameter){
        Connection conn = null;
        try {
            //1. 获取连接
            conn = JDBCUtilsByDruid.getConnection();
            //2. 执行查询语句
            return qr.query(conn, sql, new ScalarHandler<>(), parameter);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilsByDruid.close(null,null,conn);
        }

        return null;
    }

}
