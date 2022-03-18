package com.dilo.gmall.list.client;

import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.list.client.impl.ListDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-list" ,fallback = ListDegradeFeignClient.class)
public interface ListFeignClient {

    //更新商品热度
    @GetMapping("/api/list/inner/incrHotScore/{skuId}")
    public Result incrHotScore(@PathVariable Long skuId);
}
