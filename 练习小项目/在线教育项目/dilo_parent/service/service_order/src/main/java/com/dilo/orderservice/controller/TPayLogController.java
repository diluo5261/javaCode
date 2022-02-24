package com.dilo.orderservice.controller;


import com.dilo.commonutils.R;
import com.dilo.orderservice.service.TPayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author dilo
 * @since 2022-02-23
 */
@Api(description="支付管理")
@RestController
@RequestMapping("/orderservice/paylog")
@CrossOrigin
public class TPayLogController {

    @Autowired
    private TPayLogService logService;

    @ApiOperation(value = "根据订单编号生成支付二维码")
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo){

        Map<String, Object> map = logService.createNative(orderNo);
        return R.ok().data(map);
    }

    @ApiOperation("根据订单编号查询订单状态")
    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo){
        //1.调用微信接口查询支付状态
        Map<String,String> map = logService.queryPayStatus(orderNo);

        //2.判断支付状态
        if (map == null){
            return R.error().message("支付出错");
        }

        //支付成功以后,更新订单状态,记录支付日志

        if ("SUCCESS".equals(map.get("trade_state"))){
            //3.支付成功以后,更新顶到状态
            logService.updateOrderStatus(map);
            return R.ok().message("支付中");
        }

        return R.ok().code(25000).message("支付中");
    }



}

