package com.dilo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.RequestWrapper;

/**
 * @Controller : 创建处理器对象，对象放在springmvc容器中
 *
 * 位置：放在类的上面
 */
@Controller
public class MyController {
    /*
        处理用户提交的请求，springmvc 中是使用方法来处理的
        方法是自定义的，可以有多种返回值，多种参数，方法名是自定义的


     */

    /**
     * 准备使用 dosome方法处理dosome.do请求
     * @RequestMapping ： 请求映射，作用是把一个请求地址和一个方法绑定在一起
     *                      一个请求指定一个方法处理
     *
     *             属性 1. value：是一个String ，表示请求的uri地址的（some.do）
     *                      value 的值是唯一的，不能重复。在使用时，推荐地址以“/” 开头
     *
     *             位置： 1.在方法的上面，常用
     *                   2.在类的上面
     *
     *     说明：使用RequestMapping 修饰的方法叫做 处理器方法或者控制器方法
     *     使用 @RequestMapping 修饰的方法可以处理请求的，类似Servlet 中的doGEt  doPost
     *
     * @return
     */

    /**
     *
     * @return  ：ModelView ： 表示本次请求的处理结果
     *          Model : 数据，请求完成处理后，要显示给用户的数据
     *          View  ： 视图，比如 jsp 等等。
     */
    @RequestMapping("/some.do")
    public ModelAndView doSome(){
        //doGet -- service 请求处理
        //相当于service调用处理完成了

        ModelAndView mv = new ModelAndView();
        //添加数据,框架在请求的最后把数据放入到 request 作用域
        //request.setAttribute()
        mv.addObject("msg","欢迎使用springmvc 的web 开发");
        mv.addObject("fun","执行的时dosome方法");

        //指定视图,指定视图的完整路径
        //框架对视图执行的 forward 操作, request.getRequestDispather("/show.jsp).forward(...)
//        mv.setViewName("/WEB-INF/view/show.jsp");

        //当配置了视图解析器后,可以使用逻辑名称(文件名),指定视图
        //框架会使用视图解析器的前缀 + 逻辑名称 + 后缀 组成完整路径,这里就是字符连接操作

        // /WEB-INF/view/ + show + .jsp
        mv.setViewName("show");

        //返回mv
        return mv;
    }

    @RequestMapping("/other.do")
    public ModelAndView otherSome(){

        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc 的web 开发");
        mv.addObject("fun","执行的时othersome方法");


        mv.setViewName("other");


        return mv;
    }
}
