package com.dilo.controller;

import com.dilo.domain.Address;
import com.dilo.domain.Order;
import com.dilo.service.ShopService;
import com.dilo.vo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/buy")
public class ShopController {

    @Autowired
    private ShopService shopService;

    //购买商品
    @RequestMapping("/goods")
    public ModelAndView buyGoods(Integer userId, Goods goods){
        ModelAndView modelAndView = new ModelAndView();
        //调用shopService的芳芳,完成创建订单
        Order order = shopService.buyGoods(userId, goods);
        modelAndView.addObject("myorder",order);

        modelAndView.setViewName("view-order");
        return modelAndView;
    }

    //获取地址
    @RequestMapping("/getAddress")
    public ModelAndView getAddress(Integer userId){
        ModelAndView modelAndView = new ModelAndView();

        List<Address> addressList= shopService.getAddress(userId);
        //把数据放入到Model

        modelAndView.addObject("list",addressList);
        modelAndView.setViewName("view-address");

        return modelAndView;
    }

}
