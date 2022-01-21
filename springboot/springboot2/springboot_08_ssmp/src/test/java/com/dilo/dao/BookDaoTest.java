package com.dilo.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dilo.dao.BookDao;
import com.dilo.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDaoTest {

  @Autowired
  BookDao bookDao;

    @Test
    void testGetById(){
      System.out.println(bookDao.selectById(3));
    }

    @Test
  void testSave(){
      Book book = new Book();
      book.setAuthor("tom");
      book.setName("tom");
      book.setPrice(33.3);

      int result = bookDao.insert(book);
      System.out.println(book.getId());
      System.out.println(result);
    }

    @Test
    void testGetPage(){

      IPage<Book> page = new Page(2,3);
      bookDao.selectPage(page, null);

    }

    @Test
    void testGetBy(){
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.like("name","java");
        bookDao.selectList(wrapper);

    }



}
