package com.dilo.gmall.all.controller;

import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.item.client.ItemFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class ItemController {

    @Autowired
    private ItemFeignClient itemFeignClient;

    @RequestMapping("{skuId}.html")
    public String getItem(@PathVariable Long skuId, Model model){

        Result<Map> res = itemFeignClient.getItem(skuId);
        model.addAllAttributes(res.getData());

        //返回item目录下的视图名称
        return "item/index";

    }
}
