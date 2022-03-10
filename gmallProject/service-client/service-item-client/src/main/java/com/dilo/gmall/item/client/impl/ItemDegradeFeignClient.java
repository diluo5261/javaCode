package com.dilo.gmall.item.client.impl;

import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.item.client.ItemFeignClient;
import org.springframework.stereotype.Component;

@Component
public class ItemDegradeFeignClient implements ItemFeignClient {
    @Override
    public Result getItem(Long skuId) {
        return null;
    }
}
