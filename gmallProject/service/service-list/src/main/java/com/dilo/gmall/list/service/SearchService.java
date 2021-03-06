package com.dilo.gmall.list.service;

public interface SearchService {

    /**
     * 上架商品列表
     * @param skuId
     */
    void upperGoods(Long skuId);

    /**
     * 下架商品列表
     * @param skuId
     */
    void lowerGoods(Long skuId);

    /**
     * 跟新热点
     * @param skuId
     */
    void incrHotScore(Long skuId);
}
