package com.dilo.web;

import com.dilo.domain.Book;
import com.dilo.service.BookService;
import com.dilo.service.impl.BookServiceImpl;
import com.dilo.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    //添加
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求参数,封装为book对象
        Book book = WebUtils.copyParamTOBean(request.getParameterMap(),new Book());

        //2.调用bookService.addBook(book)保存图书
        bookService.add(book);

        //3.跳转到图书列表页面
        //request.getRequestDispatcher("/manager/bookServlet?action=list").forward(request,response);
        System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");
    }

    //删除
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数的id
        String id = request.getParameter("id");

        //2.调用bookService的delete方法删除图书
        bookService.deleteBookBuId(Integer.parseInt(id));

        //3.重定向会图书管理列表
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");
    }


    //查询
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过bookservice查询全部图书
        List<Book> books = bookService.queryBooks();

        //2.将全部图书保存到request域中
        request.setAttribute("books",books);

        //3.请求转发到pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

    }

    //修改
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    1、获取请求参数 == 封装成Book对象
        Book book = WebUtils.copyParamTOBean(request.getParameterMap(),new Book());

    //    2、调用bookService的update方法更新数据库
        bookService.updateBook(book);

    //    3、重定向到图书管理列表

        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");

    }

    //根据id查询图书
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数-图书编号
        int id = Integer.parseInt(request.getParameter("id"));

        //2.调用 bookService。queryBookByid
        Book book = bookService.queryBookBuId(id);

        //3.将图书信息保存到request域中
        request.setAttribute("book",book);

        //4.转发到/pages/manager/book_edit.jsp
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }

}
