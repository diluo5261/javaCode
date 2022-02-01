package com.dilo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dilo.service.TravelItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("travelItem")
public class TravelItemController {

    //这是一个坑,表示远程调用
    @Reference
    private TravelItemService itemService;


}
