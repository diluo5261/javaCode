package com.dilo.gmall.item.controller;

import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.item.service.ItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api("商品详情接口")
@RestController
@RequestMapping("/api/item")
public class ItemApiController {

    @Autowired
    private ItemService itemService;


    //没有api接口文档,以后要靠自己设计
    //编写一个控制器给weball使用,原来一直使用返回数据对象是result,也可以直接返回map
    @GetMapping("{skuId}")
    public Result getItem(@PathVariable Long skuId){
        //调用服务层的方法
        Map<String, Object> map = itemService.getBySkuId(skuId);
        return Result.ok(map);
    }
}
