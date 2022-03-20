package com.dilo.gmall.payment.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.dilo.gmall.model.enums.PaymentStatus;
import com.dilo.gmall.model.enums.PaymentType;
import com.dilo.gmall.model.order.OrderInfo;
import com.dilo.gmall.model.payment.PaymentInfo;
import com.dilo.gmall.order.client.OrderFeignClient;
import com.dilo.gmall.payment.config.AlipayConfig;
import com.dilo.gmall.payment.service.AlipayService;
import com.dilo.gmall.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Autowired
    private PaymentService paymentService;

    @Override
    public String createAliPay(Long orderId) {

        OrderInfo orderInfo = orderFeignClient.getOrderInfo(orderId);

        //生成二维码的同时,保存交易记录
        paymentService.savePaymentInfo(orderInfo, PaymentType.ALIPAY.name());

        //1.alipayClient已经有spring容器进行创建,
        //AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);  //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest(); //创建API对应的request

        /*
        1.生产二维码 !取消订单:5秒钟
        2.取消订单了,则不能生产二维码
         */
        if ("CLOSED".equals(orderInfo.getOrderStatus())){
            return "该订单已经取消";
        }

        //同步回调
        alipayRequest.setReturnUrl(AlipayConfig.return_payment_url);
        //异步回调
        alipayRequest.setNotifyUrl(AlipayConfig.notify_payment_url); //在公共参数中设置回跳和通知地址

        HashMap<String, Object> map = new HashMap<>();
        map.put("out_trade_no",orderInfo.getOutTradeNo());
        map.put("product_code","FAST_INSTANT_TRADE_PAY");
        map.put("total_amount",orderInfo.getTotalAmount());
        map.put("subject",orderInfo.getTradeBody());

        String jsonString = JSON.toJSONString(map);

        //封装的业务参数,需要json字符串
        alipayRequest.setBizContent(jsonString); //填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return form;
    }

    @Override
    public boolean refund(Long orderId) {

        OrderInfo orderInfo = orderFeignClient.getOrderInfo(orderId);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

        JSONObject bizContent = new JSONObject();
        bizContent.put("out_request_no", orderInfo.getOutTradeNo());
        bizContent.put("refund_amount", orderInfo.getTotalAmount());
        bizContent.put("out_request_no", "HZ01RF001");


        request.setBizContent(bizContent.toString());
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
            //电商平台订单关闭
            PaymentInfo paymentInfo = new PaymentInfo();
            paymentInfo.setPaymentStatus(PaymentStatus.ClOSED.name());
            this.paymentService.updatePayMentInfo(orderInfo.getOutTradeNo(),PaymentType.ALIPAY.name(),paymentInfo);
            //订单状态关闭
            return true;
        } else {
            System.out.println("调用失败");
            return false;
        }
    }

    @Override
    public boolean closePay(Long orderId) {
        //创建关闭对象
        OrderInfo orderInfo = orderFeignClient.getOrderInfo(orderId);
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        HashMap<String, Object> map = new HashMap<>();

        // map.put("trade_no",paymentInfo.getTradeNo()); // 从paymentInfo 中获取！
        map.put("out_trade_no",orderInfo.getOutTradeNo());
        map.put("operator_id","YX01");
        request.setBizContent(JSON.toJSONString(map));

        AlipayTradeCloseResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        if(response.isSuccess()){
            System.out.println("调用成功");
            return true;
        } else {
            System.out.println("调用失败");
            return false;
        }
    }

    @Override
    public boolean checkPayment(Long orderId) {
        // 根据订单Id 查询订单信息
        OrderInfo orderInfo = orderFeignClient.getOrderInfo(orderId);

        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        HashMap<String, Object> map = new HashMap<>();
        map.put("out_trade_no",orderInfo.getOutTradeNo());
        // 根据out_trade_no 查询交易记录
        request.setBizContent(JSON.toJSONString(map));
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            return true;
        } else {
            return false;
        }
    }
}
