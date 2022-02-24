package com.dilo.orderservice.client;

import com.dilo.commonutils.vo.UcenterMemberForOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //根据memberId获取用户信息跨模块
    @GetMapping("/eduvod/video/getUncenterForOrder/{memberId}")
    public UcenterMemberForOrder getUncenterForOrder(@PathVariable("memberId") String memberId);
}
