package com.dilo.gmall.item.service.impl;

import com.alibaba.fastjson.JSON;
import com.dilo.gmall.item.service.ItemService;
import com.dilo.gmall.model.product.BaseCategoryView;
import com.dilo.gmall.model.product.SkuInfo;
import com.dilo.gmall.model.product.SpuSaleAttr;
import com.dilo.gmall.product.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class ItemServiceImpl implements ItemService {

    //远程调用service-client
    @Autowired
    private ProductFeignClient productFeignClient;

//使用异步编排
    @Override
    public Map<String, Object> getBySkuId(Long skuId) {
        //声明对象
        HashMap<String, Object> result = new HashMap<>();

        CompletableFuture<SkuInfo> skuInfoCompletableFuture = CompletableFuture.supplyAsync(() -> {
            //获取到的数据是skuInfo+skuImageList
            SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
            result.put("skuInfo",skuInfo);
            //返回数据
            return skuInfo;
        });

        //Consumer void accept(T t)
        CompletableFuture<Void> categoryCompletableFuture = skuInfoCompletableFuture.thenAcceptAsync(skuInfo -> {
            //获取分类数据
            BaseCategoryView categoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());
            result.put("categoryView",categoryView);
        });

        CompletableFuture<Void> spuCompletableFuture = skuInfoCompletableFuture.thenAcceptAsync(skuInfo -> {
            //获取销售属性+销售属性值
            List<SpuSaleAttr> spuSaleAttrListCheckBySku = productFeignClient.getSpuSaleAttrListCheckBySku(skuId, skuInfo.getSpuId());
            result.put("spuSaleAttrList",spuSaleAttrListCheckBySku);

        });

        CompletableFuture<Void> skuCompletableFuture = skuInfoCompletableFuture.thenAcceptAsync(skuInfo -> {
            //查询销售属性值Id与skuId组合的map
            Map skuValueIdsMap = productFeignClient.getSkuValueIdsMap(skuInfo.getSpuId());
            //将map转换为页面需要的json对象
            String jsonString = JSON.toJSONString(skuValueIdsMap);
            result.put("valuesSkuJson",jsonString);

        });

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            //获取价格
            BigDecimal skuPrice = productFeignClient.getSkuPrice(skuId);
            //map中key对应的是谁?Thymeleaf 获取数据的时候${skuInfo.skuName}
            result.put("price",skuPrice);
        });

        //使用多任务进行组合
        CompletableFuture.allOf(skuInfoCompletableFuture,
                categoryCompletableFuture,
                spuCompletableFuture,
                skuCompletableFuture,
                completableFuture).join();

        return result;
    }

    //为使用异步编排
    /*@Override
    public Map<String, Object> getBySkuId(Long skuId) {
        //声明对象
        HashMap<String, Object> result = new HashMap<>();
        //获取到的数据是skuInfo+skuImageList
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);

        //,判断skuInfo不为空
        if (skuInfo != null){
            //获取分类数据
            BaseCategoryView categoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());
            result.put("categoryView",categoryView);

            //获取销售属性+销售属性值
            List<SpuSaleAttr> spuSaleAttrListCheckBySku = productFeignClient.getSpuSaleAttrListCheckBySku(skuId, skuInfo.getSpuId());
            result.put("spuSaleAttrList",spuSaleAttrListCheckBySku);

            //查询销售属性值Id与skuId组合的map
            Map skuValueIdsMap = productFeignClient.getSkuValueIdsMap(skuInfo.getSpuId());
            //将map转换为页面需要的json对象
            String jsonString = JSON.toJSONString(skuValueIdsMap);
            result.put("valuesSkuJson",jsonString);
        }
        //获取价格
        BigDecimal skuPrice = productFeignClient.getSkuPrice(skuId);
        //map中key对应的是谁?Thymeleaf 获取数据的时候${skuInfo.skuName}
        result.put("price",skuPrice);

        result.put("skuInfo",skuInfo);
        return result;
    }*/
}
