package com.dilo.gmall.list.service.impl;

import com.dilo.gmall.common.service.RabbitService;
import com.dilo.gmall.list.repository.GoodsRepository;
import com.dilo.gmall.list.service.SearchService;
import com.dilo.gmall.model.list.Goods;
import com.dilo.gmall.model.list.SearchAttr;
import com.dilo.gmall.model.product.*;
import com.dilo.gmall.product.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void upperGoods(Long skuId) {

        //TODO 异步编排
        Goods goods = new Goods();

        //查询sku信息
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);

        if (skuInfo != null){
            goods.setDefaultImg(skuInfo.getSkuDefaultImg());
            goods.setPrice(skuInfo.getPrice().doubleValue());
            goods.setId(skuInfo.getId());
            goods.setTitle(skuInfo.getSkuName());
            goods.setCreateTime(new Date());

            //查询品牌信息
            BaseTrademark tradeMarkById = productFeignClient.getTradeMarkById(skuInfo.getTmId());
            if (tradeMarkById != null){
                goods.setTmId(skuInfo.getTmId());
                goods.setTmName(tradeMarkById.getTmName());
                goods.setTmLogoUrl(tradeMarkById.getLogoUrl());
            }

            //查询分类
            BaseCategoryView categoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());
            if (categoryView != null){
                goods.setCategory1Id(categoryView.getCategory1Id());
                goods.setCategory1Name(categoryView.getCategory1Name());
                goods.setCategory2Id(categoryView.getCategory2Id());
                goods.setCategory2Name(categoryView.getCategory2Name());
                goods.setCategory3Id(categoryView.getCategory3Id());
                goods.setCategory3Name(categoryView.getCategory3Name());
            }
        }


        //查询sku对应的平台属性
        List<BaseAttrInfo> attrList = productFeignClient.getAttrList(skuId);

        if (attrList != null){

            List<SearchAttr> searchAttrList = attrList.stream().map(baseAttrInfo -> {
                SearchAttr searchAttr = new SearchAttr();
                searchAttr.setAttrId(baseAttrInfo.getId());
                searchAttr.setAttrName(baseAttrInfo.getAttrName());

                //一个sku只对应一个属性值
                List<BaseAttrValue> baseAttrValueList = baseAttrInfo.getAttrValueList();
                searchAttr.setAttrValue(baseAttrValueList.get(0).getValueName());

                return searchAttr;
            }).collect(Collectors.toList());

            goods.setAttrs(searchAttrList);
        }


        goodsRepository.save(goods);
    }

    @Override
    public void lowerGoods(Long skuId) {
        goodsRepository.deleteById(skuId);
    }

    @Override
    public void incrHotScore(Long skuId) {
        //定义可以
        String hotKey = "hotScore";
        //保存数据
        Double hotScore = redisTemplate.opsForZSet().incrementScore(hotKey, "skuId:" + skuId, 1);
        if (hotScore %10 == 0){
            //从es中获取数据
            Optional<Goods> optional = goodsRepository.findById(skuId);
            Goods goods = optional.get();
            goods.setHotScore(hotScore.longValue());

            this.goodsRepository.save(goods);
        }



    }
}
