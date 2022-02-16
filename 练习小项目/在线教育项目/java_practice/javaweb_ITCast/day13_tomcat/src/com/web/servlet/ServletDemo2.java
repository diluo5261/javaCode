package com.web.servlet;

import javax.servlet.*;
import java.io.IOException;

public class ServletDemo2 implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init ...");

    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("config ...");
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        System.out.println("servler ...");
    }

    @Override
    public String getServletInfo() {
        System.out.println("servlet information ...");
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destory ...");

    }
}
