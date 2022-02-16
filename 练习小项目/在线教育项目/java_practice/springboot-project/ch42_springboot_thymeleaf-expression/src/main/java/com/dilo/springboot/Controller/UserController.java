package com.dilo.springboot.Controller;

import com.dilo.springboot.domain.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @RequestMapping("/user/detail")
    public ModelAndView userDetail(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        user.setName("张三");
        user.setId(15);
        user.setAge(25);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("detail");
        return modelAndView;
    }

    @RequestMapping("/url")
    public ModelAndView url(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        user.setName("张三");
        user.setId(15);
        user.setAge(25);
        modelAndView.addObject("username","后台获取的参数");
        modelAndView.addObject("user",user);
        modelAndView.setViewName("url");

        return modelAndView;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(String username){
        return "请求路径/test,参数是 :"+username;
    }

    @RequestMapping("/test1")
    @ResponseBody
    public String test1(String username,Integer id,Integer age){
        return "请求路径/test,姓名:"+username+"年龄:"+age+"id : "+id;
    }

    @RequestMapping("/url2")
    public String url2(){
        return "url2";
    }

    @RequestMapping("/property")
    public String property(){
        return "property";
    }
}
