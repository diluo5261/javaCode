package com.dilo.dao.Impl;

import com.dilo.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    //使用dbutils使用操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 执行insert.update.delete语句
     * @return 返回 -1 执行失败
     */
    public int update(String sql,Object... args){
        Connection connection = null;
        try {
            connection= JdbcUtils.getConnection();
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
       /* finally {
            JdbcUtils.close(connection);
        }*/
        //return -1;
    }

    /**
     * 查询返回一个javaBean的sql语句
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql ,Object... args){
        Connection connection = null;
        try {
           connection = JdbcUtils.getConnection();
           QueryRunner queryRunner = new QueryRunner();
           return queryRunner.query(connection,sql,new BeanHandler<>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
       /* finally {
            JdbcUtils.close(connection);
        }*/
        //return null;
    }

    /**
     * 查询返回一组数据javaBean
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T>List<T> queryForList(Class<T> type,String sql ,Object... args){
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            return queryRunner.query(connection,sql,new BeanListHandler<>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        /*finally {
            JdbcUtils.close(connection);
        }*/
        //return null;
    }

    /**
     * 执行返回一行一列的查询语句
     * @param sql
     * @param args
     * @return
     */
    public Object queryForSingleValue(String sql,Object... args){
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
           return  queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        /*finally {

            JdbcUtils.close(connection);
        }*/
        //return null;
    }
}
