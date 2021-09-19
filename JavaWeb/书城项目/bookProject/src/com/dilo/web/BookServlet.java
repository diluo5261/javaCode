package com.dilo.web;

import com.dilo.domain.Book;
import com.dilo.service.BookService;
import com.dilo.service.impl.BookServiceImpl;
import com.dilo.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends  BaseServlet {


    private BookService bookService = new BookServiceImpl();
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //1. 获取请求参数,封装为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2. 调用BookService 添加图书
        bookService.addBook(book);

        //3. 跳转到列表页面
//        list(req, resp);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");



    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数的id
        String id = req.getParameter("id");

        //2.调用bookService.deleteBookById();删除图书
        bookService.deleteBookById(Integer.parseInt(id));
        //3. 重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");

    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数,封装成book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        //2.调用BookService.updateBook(book);修改图书
        bookService.updateBook(book);

        //3.重定向回图书列表管理页面
        //地址:/项目名/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过 bookServlet查询全部如数
        List<Book> books = bookService.queryBooks();

        //2. 将全部图书信息保存到request域
        req.setAttribute("books",books);

        //3.请求转发到/pages/manager/book_mamager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);


    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数的图书编号
        String id = req.getParameter("id");

        //2.调用bookservice.queryBookById查询图书
        Book book = bookService.queryBookById(Integer.parseInt(id));

        //3.保存图书到request域中
        req.setAttribute("book",book);

        //4.请求转发.pages/manager/book_edit.jsp

        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);


    }

}

