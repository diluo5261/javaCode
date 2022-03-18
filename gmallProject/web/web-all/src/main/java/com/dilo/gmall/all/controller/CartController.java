package com.dilo.gmall.all.controller;

import com.dilo.gmall.cart.client.CartFeignClient;
import com.dilo.gmall.model.product.SkuInfo;
import com.dilo.gmall.product.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {

    @Autowired
    private CartFeignClient cartFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @GetMapping("addCart.html")
    public String addCart(HttpServletRequest request){
        String skuId = request.getHeader("skuId");
        String skuNum = request.getHeader("skuNum");

        //远程调用购物车接口
        cartFeignClient.addToCart(Long.parseLong(skuId),Integer.parseInt(skuNum));

        //存储skuInfo对象
        SkuInfo skuInfo = productFeignClient.getSkuInfo(Long.parseLong(skuId));
        request.setAttribute("skuInfo",skuInfo);
        request.setAttribute("skuNum",skuNum);

        //返回视图名
        return "cart/addCart";
    }

    /**
     * 查看购物车
     * @return
     */
    @RequestMapping("cart.html")
    public String index(){
        //返回购物车列表页面
        return "cart/index";
    }



}
