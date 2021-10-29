package com.dilo.web;

import com.dilo.domain.Cart;
import com.dilo.domain.User;
import com.dilo.service.OrderService;
import com.dilo.service.impl.OrderServiceImpl;

import javax.jnlp.IntegrationService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();

    protected void creatOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取 Cart 购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        //2.获取用户 Id
        User login = (User) request.getSession().getAttribute("user");

        if (login == null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            return;
        }

        Integer userId = login.getId();
        //调用orderService.creatOrder(Cart,id)生成订单
        String orderId = orderService.createOrder(cart,userId);

        //使用重定向会出现，表单重复提交的问题
        //request.setAttribute("orderId",orderId);
        //request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);

        request.getSession().setAttribute("orderId",orderId);
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
