package com.dilo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dilo.dao.BookDao;
import com.dilo.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SSPApplicationTests {

    @Autowired
    private BookDao bookDao;

    @Test
    void testSave(){
        Book book = new Book();
        book.setName("金瓶666梅");
        book.setAuthor("兰陵萧先生");
        book.setPrice(99);
       int res = bookDao.insert(book);
        System.out.println(res>0?"插入成功!":"插入失败!");
    }

    @Test
    void testSelectById() {
        Book book = bookDao.selectById(7);
        System.out.println(book);
    }

    @Test
    void testPage(){
        IPage page = new Page(1,5);

        bookDao.selectPage(page,null);
        //操作之后,返回的时ipage对象,直接从page对象中获取对象即可
        System.out.println(page.getPages());
        System.out.println(page.getCurrent());
        System.out.println(page.getRecords());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());

    }

    @Test
    void testByCondition(){
        String name = "";
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        //wrapper.like("name","金瓶梅");
        wrapper.like(name!= null,"name","金瓶梅");
        bookDao.selectList(wrapper);
    }

    @Test
    void testByCondition2(){

        //LambdaQueryWrapper<Book> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //lambdaQueryWrapper.like(Book::getName,"金瓶梅");
        //bookDao.selectList(lambdaQueryWrapper);

        //在实际中,可能会传进来一个空值
        String name ="1";
        LambdaQueryWrapper<Book> bookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        bookLambdaQueryWrapper.like(name!=null,Book::getName,"金瓶梅");
        bookDao.selectList(bookLambdaQueryWrapper);
    }


}
