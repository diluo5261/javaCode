package com.dilo.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//拦截器类，拦截用户的请求
public class MyInterceptor implements HandlerInterceptor {
    /*
    * preHandle叫做预处理方法。
     *   重要：是整个项目的入口，门户。 当preHandle返回true 请求可以被处理。
     *        preHandle返回false，请求到此方法就截止。
     *
     * 参数：
     *  Object handler ： 被拦截的控制器对象
     * 返回值boolean
     *   true：请求是通过了拦截器的验证，可以执行处理器方法。
         *   拦截器的MyInterceptor的preHandle()
             =====执行MyController中的doSome方法=====
             拦截器的MyInterceptor的postHandle()
             拦截器的MyInterceptor的afterCompletion()
         *
     *   false：请求没有通过拦截器的验证，请求到达拦截器就截止了。 请求没有被处理
     *      拦截器的MyInterceptor的preHandle()
     *
     *
     *  特点：
     *   1.方法在控制器方法（MyController的doSome）之前先执行的。
     *     用户的请求首先到达此方法
     *
     *   2.在这个 方法中可以获取请求的信息， 验证请求是否符合要求。
     *     可以验证用户是否登录， 验证用户是否有权限访问某个连接地址（url）。
     *      如果验证失败，可以截断请求，请求不能被处理。
     *      如果验证成功，可以放行请求，此时控制器方法才能执行。
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器MyInterceptor的preHandle方法！");
        request.getRequestDispatcher("/tips.jsp").forward(request,response);
        return true;
    }

    /*
    可以对原来的执行结果进行修改，
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null){
            //修改数据
            modelAndView.addObject("mydate",new Date());
            //修改视图
            modelAndView.setViewName("other");

        }
        System.out.println("拦截器MyInterceptor的postHandle方法！");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器MyInterceptor的afterCompletion方法！");
    }
}
