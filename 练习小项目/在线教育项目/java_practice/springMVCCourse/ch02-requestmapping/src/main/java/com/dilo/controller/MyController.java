package com.dilo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * @RequestMapping : 请求映射
     *                  属性:method,表示请求的方式.它的值RequestMethod类枚举值
     *                  例如表示get请求方式,RequestMethod.GET
     *                  post 方式,RequestMethod.POST
     *                  不加method,get和post请求都可以
     * @return
     */
    @RequestMapping(value = "/some.do",method = RequestMethod.GET)
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

    //如果需要使用参数,直接加上参数就可以使用了
    @RequestMapping(value = "/other.do" ,method = RequestMethod.POST)
    public ModelAndView otherSome(HttpServletRequest request,
                                  HttpServletResponse response,
                                  HttpSession session){

        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc 的web 开发");
        mv.addObject("fun","执行的时othersome方法");


        mv.setViewName("other");

        return mv;
    }
}
