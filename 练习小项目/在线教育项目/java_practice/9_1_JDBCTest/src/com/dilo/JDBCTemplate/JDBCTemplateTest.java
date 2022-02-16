package com.dilo.JDBCTemplate;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.dilo.test.JDBCUtilsByDruid;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JDBCTemplateTest {

    public static void main(String[] args) throws Exception {
        // JDBCTemplate是由Spring简单的封装的JDBC
        //1.导入jar包
        //2. 创建JDBCTemplate对象
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidDataSourceFactory.createDataSource(new Properties().load(new FileInputStream("druid.properties"))));
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/druid.properties"));

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //3. 执行sql语句
        String sql = "update user set name = '哆啦A梦' where id = ?";
        int count = jdbcTemplate.update(sql,8);
        System.out.println("成功执行"+ count+"条语句");
    }

    /**
     * 修改8号数据的qq为88888888
     */
    @Test
    public void update(){
        //1. 获取jdbcTemplate
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtilsByDruid.getDataSource());

        //2. 执行sql语句
        String sql = "update user set qq = ? where id =?";
        int ret = jdbcTemplate.update(sql,88888888,8);
        System.out.println("成功修改"+ret+"条语句");

    }

    //添加一条记录
    @Test
    public void insert(){
        //1. 获取jdbcTemplate连接
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtilsByDruid.getDataSource());
        //2. 执行sql语句
        String sql = "insert into user values(9,'天下霸唱','男','中国','52525252','69696969')";
        int ret = jdbcTemplate.update(sql);
        System.out.println("成功插入"+ret+"条语句");
    }

    //删除刚才的记录
    @Test
    public void delete(){
        //1. 获取jdbcTemplate连接
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtilsByDruid.getDataSource());

        //2. 执行sql语句
        String sql ="delete from user where id = ?";
        int ret = jdbcTemplate.update(sql,9);
        System.out.println("成功删除"+ret+"条语句");
    }


//    查询name为1的记录，将其封装为Map集合
    @Test
    public void query(){
        //1. 获取jdbcTemplate连接
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtilsByDruid.getDataSource());

        //2.执行sql语句
        String sql = "select * from user where id = 5";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql);
        System.out.println(map);

    }

    //查询所有记录，封装为list
    @Test
    public void queryAll(){
        //1. 获取jdbcTemplate连接
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtilsByDruid.getDataSource());
        //2. 执行sql语句
        String sql = "select * from user";

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        for (Map<String ,Object> map :maps){
            System.out.println(map);
        }
    }

    //查询所有记录，将其封装为Emp对象的List集合
    @Test
    public void queryByObject(){
        //1. 获取jdbcTemplate连接对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtilsByDruid.getDataSource());
        //2. 执行sql语句
        String sql = "select * from user";

        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
        for (User user : list){
            System.out.println(user);
        }
    }

    // 查询总记录数
    @Test
    public void queryObject(){
        //1. 获取jdbcTemplate连接对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtilsByDruid.getDataSource());

        //执行sql语句
        String sql = "select count(name )  from user";

        Long aLong = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(aLong);
    }
}
