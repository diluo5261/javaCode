package com.dilo.gmall.cart.client;


import com.dilo.gmall.cart.client.impl.CartDegradeFeignClient;
import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.model.cart.CartInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "service-cart", fallback = CartDegradeFeignClient.class)
public interface CartFeignClient {

    @PostMapping("/api/cart/addToCart/{skuId}/{skuNum}")
    Result addToCart(@PathVariable("skuId") Long skuId, @PathVariable("skuNum") Integer skuNum);

    @GetMapping("/api/cart/getCartCheckedList/{userId}")
    public List<CartInfo> getCartCheckedList(@PathVariable(value = "userId") String userId);

    //根据userId查询最新价格
    @GetMapping("/api/cart/loadCartCache/{userId}")
    Result loadCartCache(String userId);


}
