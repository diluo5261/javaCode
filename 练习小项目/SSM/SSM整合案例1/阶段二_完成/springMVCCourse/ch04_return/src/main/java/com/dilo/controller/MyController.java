package com.dilo.controller;

import com.dilo.domain.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Controller : 创建处理器对象，对象放在springmvc容器中
 * 位置：放在类的上面
 * 能处理请求的都是控制器(处理器) : MyController 能处理请求,叫做后端控制器(back controller)
 */
@Controller
public class MyController {


    /**
     * 方法返回String 表示视图的逻辑名称,需要配置视图解析器
     * @param username
     * @param userage
     * @return
     */
    @RequestMapping("/returnString-View.do")
    public String doReturnString(HttpServletRequest request,String username, Integer userage){
        //如果想传递数据,可以自己加入request域中
        request.setAttribute("name",username);
        request.setAttribute("age",userage);
        System.out.println("doReturnView, name="+username+"age:"+userage);
        //框架对视图执行forward转发操作
        return "show";
    }


    /*
        处理器方法返回String,表示完整视图路径,此时不能配置视图解析器
     */
    @RequestMapping("/returnString-View2.do")
    public String doReturnString2(HttpServletRequest request,String username, Integer userage){
        //如果想传递数据,可以自己加入request域中
        request.setAttribute("name",username);
        request.setAttribute("age",userage);
        System.out.println("doReturnView, name="+username+"age:"+userage);
        //视图中不能配置视图解析器,否则会冲突
        //框架对视图执行forward转发操作
        return "/WEB-INF/view/show.jsp";
    }

    //处理器返回 void ,响应ajax请求
    //手工实现ajax,json数据:代码有重复的 1.java对象转换为 json 2.通过 HttpservletResponse输出json数据
    @RequestMapping("/returnVoid-ajax.do")
    public void doReturnVoid(HttpServletResponse response,String username, Integer userage) throws IOException {
        System.out.println("DoReturnVoidAjax: name="+username+"age = "+userage);


        //处理ajax,使用json做数据格式
        Student student = new Student();
        student.setRage(userage);
        student.setRname(username);

        //把对象转换为 json对象
        String json = "";
        if (student != null){
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(student);
            System.out.println("ajax请求===="+json);
        }

        //输出数据,响应ajax的请求
        response.setContentType("application/json;charset=utf-8 ");
        response.getWriter().write(json);
    }

    /**
     * 处理器方法返回一个Student,通过框架转换为json,响应ajax
     *
     * @ResponseBody:
     *          作用:把处理器方法返回对象转为json后,通过HttpServletResponse输出给浏览器
     *          位置:在方法定义的上面,和其他注解没有顺序关系
     *
     * 返回对象框架处理流程:
     *  1.框架会把返回Student 类型,调用框架中ArrayList<HttpMessageConverter>中每个类的canwrite()方法
     *      检查哪个HttpMessageConvert 接口的实现类能处理Student类型的数据--MappingJackson2HttpMessageConverter
     *
     *  2.框架会调用实现类的write(),--MappingJackson2HttpMessageConverter的write()方法
     *      把李四同学的的student对像转为json,调用jackson的ObjectMapper实现转为json
     *      contentType: application/json;charset=utf-8
     *
     *  3.框架会调用ResponseBody会把2的结果暑促到浏览器,完成ajax请求
     */
    @RequestMapping("/requestStudentJson.do")
    @ResponseBody
    public Student doStudentJsonObject(String username, Integer userage){
        //调用service,获取请求结果数据,Student对象表示结果数据
        Student student = new Student();
        student.setRname(username);
        student.setRage(userage);

        return student;//会被框架转为json

    }

    /**
     * 处理器方法返回的是 一个List集合
     *
     */

    @RequestMapping("/requestStudentArray.do")
    @ResponseBody
    public List<Student> doStudentObjectArray(String username, Integer userage){
        List<Student> list = new ArrayList<>();

        //调用service,获取请求结果数据,Student对象表示结果数据
        Student student = new Student();
        student.setRname(username);
        student.setRage(userage);
        list.add(student);

        student = new Student();
        student.setRname("王五");
        student.setRage(65);
        list.add(student);

        return list; //会被框架转为json
    }

    /**
     * 处理器方法返回的是String,String 表示数据的,不是视图
     * 区分返回值是字符还是驶入,看有没有 @ResponseBody注解
     * 如果有返回的是数据,如果没有返回的是视图
     *
     * 默认是使用 test/plain;charset=ISO-8859-1 作为contenType ,导致中文有乱码
     * 解决方案,给RequestMapping 增加一个属性 prouces,使用这个属性执行新的contextType
     */


    @RequestMapping(value = "/returnStringData.do",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String doStringData(String username,Integer userage){
        return "Hello SpringMVC返回对象,表示数据";
    }




}
