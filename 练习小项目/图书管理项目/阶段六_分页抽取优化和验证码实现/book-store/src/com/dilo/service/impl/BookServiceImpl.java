package com.dilo.service.impl;

import com.dilo.dao.BookDao;
import com.dilo.dao.Impl.BookDaoImpl;
import com.dilo.domain.Book;
import com.dilo.domain.Page;
import com.dilo.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void add(Book book) {
        bookDao.addBook(book);

    }

    @Override
    public void deleteBookBuId(Integer id) {
bookDao.deleteById(id);
    }

    @Override
    public void updateBook(Book book) {
bookDao.updateBook(book);
    }

    @Override
    public Book queryBookBuId(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        //求总的记录数
        Integer pageTotalCount = bookDao.queryForPageTotal();

        //设置总的记录数
        page.setPageTotalCount(pageTotalCount);

        //设置总页码
        Integer pageTotal = (int)Math.ceil((pageTotalCount*1.0) / pageSize);
        page.setPageTotal(pageTotal);

        if (pageNo < 1){
            pageNo =1;
        }

        if (pageNo > pageTotal){
            pageNo = pageTotal;
        }

        //设置当前页码
        page.setPageNo(pageNo);
        //设置每页显示的数量
        page.setPageSize(pageSize);

        //求当前页数据

        int begin = (page.getPageNo() -1)*pageSize;
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        //设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();

        //求总的记录数
        Integer pageTotalCount = bookDao.queryForPageTotalByPrice(min,max);

        //设置总的记录数
        page.setPageTotalCount(pageTotalCount);

        //设置总页码
        Integer pageTotal = (int)Math.ceil((pageTotalCount*1.0) / pageSize);
        page.setPageTotal(pageTotal);

        if (pageNo < 1){
            pageNo =1;
        }

        if (pageNo > pageTotal){
            pageNo = pageTotal;
        }

        //设置当前页码
        page.setPageNo(pageNo);
        //设置每页显示的数量
        page.setPageSize(pageSize);

        //求当前页数据

        int begin = (page.getPageNo() -1)*pageSize;
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        //设置当前页数据
        page.setItems(items);
        return page;



    }


}
