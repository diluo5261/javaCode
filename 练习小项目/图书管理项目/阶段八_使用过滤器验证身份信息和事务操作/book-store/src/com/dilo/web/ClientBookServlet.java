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
        page.setUrl("client/bookServlet?action=page");

        //3.保存page对象到request 域中
        request.setAttribute("page",page);

        //4.请求转发到pages/manager/book/book_manager.jsp

        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求的参数 pageNo 和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"),0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果有最小价格,追加到分页条的地址当中
        if (request.getParameter("min") != null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        //如果有最大价格,追加到分页条的地址当中
        if (request.getParameter("max") != null){
            sb.append("&max=").append(request.getParameter("max"));
        }

        //2.调用 BookService.page方法 ： page对象
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);
        page.setUrl(sb.toString());

        //3.保存page对象到request 域中
        request.setAttribute("page",page);

        //4.请求转发到pages/manager/book/book_manager.jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);

    }

}
