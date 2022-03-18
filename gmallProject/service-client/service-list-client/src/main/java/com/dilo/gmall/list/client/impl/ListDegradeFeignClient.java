package com.dilo.gmall.list.client.impl;

import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.list.client.ListFeignClient;
import org.springframework.stereotype.Component;

@Component
public class ListDegradeFeignClient implements ListFeignClient {
    @Override
    public Result incrHotScore(Long skuId) {
        return null;
    }
}
