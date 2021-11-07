package com.dilo.controller;

import com.dilo.pojo.ProductInfo;
import com.dilo.service.ProductInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    //每页显示的记录数
    public static final int PAGE_SIZE = 5;

    //切记:在界面层中,一定会有业务逻辑层的对象
    @Autowired
    private ProductInfoService productInfoService;

    //显示全部商品不分页
    @RequestMapping("/getAll")
    public ModelAndView getAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<ProductInfo> list = productInfoService.getAll();

        modelAndView.addObject("list",list);
        modelAndView.setViewName("product");

        return modelAndView;
    }

    //获取第一页的5条数据

    @RequestMapping("/split")
    public ModelAndView split(){
        ModelAndView modelAndView = new ModelAndView();

        //得到第一页的数据
        PageInfo info = productInfoService.splitPage(1,PAGE_SIZE);
        modelAndView.addObject("info",info);
        modelAndView.setViewName("product");
        return modelAndView;
    }


}
