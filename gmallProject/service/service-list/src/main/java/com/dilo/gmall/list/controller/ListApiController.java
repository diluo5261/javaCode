package com.dilo.gmall.list.controller;

import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.list.service.SearchService;
import com.dilo.gmall.model.list.Goods;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/list")
public class ListApiController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @GetMapping("inner/createIndex")
    public Result createIndex() {
        //创建索引库
        restTemplate.createIndex(Goods.class);
        restTemplate.putMapping(Goods.class);
        return Result.ok();
    }

    @ApiOperation("上架商品列表")
    @GetMapping("/inner/upperGoods/{skuId}")
    public Result upperGoods(@PathVariable Long skuId) {
        searchService.upperGoods(skuId);
        return Result.ok();

    }

    @ApiOperation("下架商品列表")
    @GetMapping("/inner/lowerGoods/{skuId}")
    public Result lowerGoods(@PathVariable Long skuId) {
        searchService.lowerGoods(skuId);
        return Result.ok();
    }

    @ApiOperation("更新商品热度")
    @GetMapping("inner/incrHotScore/{skuId}")
    public Result incrHotScore(@PathVariable Long skuId){
        searchService.incrHotScore(skuId);
        return Result.ok();
    }


}
