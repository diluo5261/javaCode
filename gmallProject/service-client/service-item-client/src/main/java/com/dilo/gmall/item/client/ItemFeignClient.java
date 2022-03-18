package com.dilo.gmall.item.client;


import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.item.client.impl.ItemDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "service-item",fallback = ItemDegradeFeignClient.class)
public interface ItemFeignClient {

    @GetMapping("/api/item/{skuId}")
    Result getItem(@PathVariable Long skuId);
}
