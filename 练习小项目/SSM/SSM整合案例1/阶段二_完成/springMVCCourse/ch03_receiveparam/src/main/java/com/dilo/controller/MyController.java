package com.dilo.controller;

import com.dilo.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

/**
 * @Controller : 创建处理器对象，对象放在springmvc容器中
 * 位置：放在类的上面
 * 能处理请求的都是控制器(处理器) : MyController 能处理请求,叫做后端控制器(back controller)
 */
@Controller
public class MyController {

   /*
   逐个接收请求参数
        要求:处理器方法的形参名和请求中参数必须一致
            同名的请求参数赋值非同名的形参

        框架接收请求参数
            1.使用request对象接收请求参数
                String name = request.getParameter("name");

            2.springmvc 框架通过 DispatcherServlet 调用 Mycontroller 的dosome方法
            调用方法时,按名称对应,把接收的参数赋值给形参

            框架会提供类型的转化功能,能把 String 转换为 int,long,float,double

    */

    @RequestMapping("/receiveproperty.do")
    public ModelAndView doService(String username, Integer userage){
        ModelAndView mv =new ModelAndView();
        mv.addObject("myname",username);
        mv.addObject("myage",userage);
        mv.setViewName("show");
        return mv;
    }

    /*
    请求中参数名和处理器方法的形参名不一样
    @RequestParam 逐个接收请求参数中,解决请求中参数名和形参名不一样的问题
        属性:
            1.value 请求中参数名称
            2.required : 是一个boolean类型,默认是true
                true:表示请求中必须包含此函数

        位置:在处理器方法的形参定义的前面
     */
    @RequestMapping("/receiveparam.do")
    public ModelAndView receiveParam(@RequestParam(value = "rname") String username, @RequestParam(value = "rage") Integer userage){
        ModelAndView mv =new ModelAndView();
        mv.addObject("myname",username);
        mv.addObject("myage",userage);
        mv.setViewName("show");
        return mv;
    }


/*
    处理器方法形参是 java 对象,这个对象的属性名和请求中测参数名一样
    框架会创建形参的java对象,给属性赋值,请求中的参数是rname,框架会调用 setRname
 */
    @RequestMapping("/receiveobject.do")
    public ModelAndView receiveParamObject(Student student){
        ModelAndView mv =new ModelAndView();
        mv.addObject("myname",student.getRname());
        mv.addObject("myage",student.getRage());
        mv.setViewName("show");
        return mv;
    }


}
