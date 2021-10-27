package com.dilo.web;

import com.dilo.domain.Book;
import com.dilo.domain.Page;
import com.dilo.service.BookService;
import com.dilo.service.impl.BookServiceImpl;
import com.dilo.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求的参数 pageNo 和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用 BookService.page方法 ： page对象
        Page<Book> page = bookService.page(pageNo,pageSize);

        //3.保存page对象到request 域中
        request.setAttribute("page",page);

        //4.请求转发到pages/manager/book/book_manager.jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

    }

}
