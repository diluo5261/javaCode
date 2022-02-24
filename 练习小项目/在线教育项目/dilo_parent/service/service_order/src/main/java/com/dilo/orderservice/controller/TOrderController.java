package com.dilo.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dilo.commonutils.R;
import com.dilo.commonutils.utils.JwtUtils;
import com.dilo.orderservice.entity.TOrder;
import com.dilo.orderservice.service.TOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author dilo
 * @since 2022-02-23
 */

@Api(description="订单管理")
@RestController
@RequestMapping("/orderservice/order")
@CrossOrigin
public class TOrderController {

    @Autowired
    private TOrderService orderService;

    @ApiOperation("根据课程id,用户id创建订单")
    @PostMapping("createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = orderService.createOrder(courseId,memberId);
        return R.ok().data("orderNo",orderNo);
    }

    @ApiOperation("根据订单编号查询订单信息")
    @GetMapping("/getOrderInfo/{orderNo}")
    public R getOrderInfo(@PathVariable String orderNo){
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",orderNo);

        TOrder tOrder = orderService.getOne(queryWrapper);
        return R.ok().data("tOrder",tOrder);
    }

    @ApiOperation("根据课程id,用户id查询是否已购买,远程调用")
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId,
                               @PathVariable String memberId){

        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",1).eq("course_id",courseId).eq("member_id",memberId);

        int count = orderService.count(queryWrapper);

        if (count >0){
            return true;
        }else{
            return false;
        }

    }

}

