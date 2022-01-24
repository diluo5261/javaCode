package com.dilo;


import com.dilo.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest{
    JdbcTemplate jdbcTemplate;
    @Before
    public void init(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
         jdbcTemplate = context.getBean(JdbcTemplate.class);
    }


    @Test
    public void test01(){
        int res = jdbcTemplate.update("insert into book values (?,?,?,?)", null, "天下第一", 66.6, "少林寺");
        System.out.println(res);
    }

    //修改操作
    @Test
    public void change(){
        int res = jdbcTemplate.update("update book set  name = ? where id =?", "天下无双", 81);
        System.out.println(res >0?"修改成功":"修改失败");
    }

    //删除操作
    @Test
    public void delete(){
        int res = jdbcTemplate.update("delete from book where id =?",81);
        System.out.println(res >0?"删除成功":"删除失败");
    }

    //查询全部
    @Test
    public void queryAll(){
        List<Book> bookList = jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<Book>(Book.class));
        for (Book book:bookList) {
            System.out.println(book);
        }
    }
    
    //查询单个
    @Test
    public void queryOne(){
        Book book = jdbcTemplate.queryForObject("select * from book where id =?", new BeanPropertyRowMapper<>(Book.class), 82);
        System.out.println(book);
    }
}
