package com.dilo.gmall.item.service;

import java.util.Map;

public interface ItemService {


    //编写抽象方法,重要的是返回值,参数
    /**
     * 获取sku详情信息
     * @param skuId
     * @return
     */
    Map<String,Object> getBySkuId(Long skuId);
}
