package com.dilo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;


@Controller
public class MyController {


    /**
     * 处理返回ModelAndView ，实现转发forward
     * 语法：setviewName("forward:视图文件完整路径");
     * forward特点： 不和视图解析器一同使用，就当项目中没有视图解析器
     */
    @RequestMapping(value = "/doForward.do",method = RequestMethod.POST)
    public ModelAndView doSome(){

        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc 的web 开发");
        mv.addObject("fun","执行的时dosome方法");


        //mv.setViewName("show");

        //显示转发，可以转发到视图解析器以外的路径
        mv.setViewName("forward:/WEB-INF/view/show.jsp");

        return mv;
    }

    /**
     * 处理器方法，返回ModelAndView，实现重定向redirect
     * 语法：serViewName("redirect:视图完整路径")
     * redirect特点：不和视图解析器一同使用，就当项目中没有视图解析器
     *
     * 框架对重定向的操作
     * 1、框架火把Model中的简单类型的数据，转为字符串使用，作为hello.jsp的get请求参数使用
     *  目的是在doRedirect。do和 hello。jsp中传递数据
     *
     * 2、在目标hello.jsp页面可以使用参数集合对象 ${param}获取请求参数值
     *    ${param.myname}目标中hello。jsp页面中可以使用参数集合对象
     */

    @RequestMapping(value = "/doRedirect.do",method = RequestMethod.POST)
    public ModelAndView doRedirect(String name,Integer age){

        ModelAndView mv = new ModelAndView();
        mv.addObject("myname",name);
        mv.addObject("myage",age);


        //mv.setViewName("show");

        //重定向
        mv.setViewName("redirect:/hello.jsp");
        //http://localhost:8080/ch08_forward/hello.jsp?myname=lisi&myage=99

        return mv;
    }


}
