package com.dilo.gmall.order.client.impl;

import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.model.order.OrderInfo;
import com.dilo.gmall.order.client.OrderFeignClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderDegradeFeignClient implements OrderFeignClient {
    @Override
    public Result<Map<String, Object>> trade() {
        return Result.fail();
    }

    @Override
    public OrderInfo getOrderInfo(Long orderId) {
        return null;
    }
}
