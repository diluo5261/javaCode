package com.dilo.web;

import com.dilo.domain.Book;
import com.dilo.domain.Cart;
import com.dilo.domain.CartItem;
import com.dilo.service.BookService;
import com.dilo.service.impl.BookServiceImpl;
import com.dilo.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    获取请求的参数，商品编号，商品数量
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        int count = WebUtils.parseInt(request.getParameter("count"),1);

    //    获取cart购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null){
            cart.updateCount(id,count);
            response.sendRedirect(request.getHeader("Referer"));
        }

    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        //2.清空购物车
        if (cart != null){
            cart.clean();
            //重定向回原来的页面
            response.sendRedirect(request.getHeader("Referer"));
        }

    }

    /**
     * 删除商品项,
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取要删除图书的id
        int id = WebUtils.parseInt(request.getParameter("id"),0);

        //2.获取session
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        //3.删除该id的图书
        if (cart != null){
            cart.deleteItem(id);

            //4.重定向会原页面
            response.sendRedirect(request.getHeader("Referer"));
        }


    }


    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求参数,商品的编号
        int id = WebUtils.parseInt(request.getParameter("id"),0);

        //2.调用bookService.queryBookById(id):得到图书的信息
        Book book = bookService.queryBookBuId(id);

        //3.把图书信息,转化未cartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(),1,book.getPrice());

        //4.调用cart.addItem添加商品项
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }

        request.getSession().setAttribute("lastAddName",cartItem.getName());
        cart.addItem(cartItem);

        //5.重新定向回原来商品所在的列表
        response.sendRedirect(request.getHeader("Referer"));
    }


}
