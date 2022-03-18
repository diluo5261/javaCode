package com.dilo.gmall.order.client;

import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.model.order.OrderInfo;
import com.dilo.gmall.order.client.impl.OrderDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@FeignClient(value = "service-order", fallback = OrderDegradeFeignClient.class)
public interface OrderFeignClient {

    @GetMapping("api/order/auth/trade")
    Result<Map<String, Object>> trade();

    @GetMapping("api/order/inner/getOrderInfo/{orderId}")
    OrderInfo getOrderInfo(@PathVariable Long orderId);

}
