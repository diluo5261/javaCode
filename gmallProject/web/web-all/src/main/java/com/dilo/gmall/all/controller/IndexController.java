package com.dilo.gmall.all.controller;

import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.product.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private ProductFeignClient productFeignClient;

    //  http://www.gmall.com
    //  http://www.gmall.com/index.html
    @GetMapping({"/","index.html"})
    public String index(HttpServletRequest request){
        Result res = productFeignClient.getBaseCategoryList();
        request.setAttribute("list",res.getData());
        return "index/index";
    }
}
