package com.dilo.orderservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dilo.baseservice.handler.DiloException;
import com.dilo.commonutils.ResultCode;
import com.dilo.orderservice.entity.TOrder;
import com.dilo.orderservice.entity.TPayLog;
import com.dilo.orderservice.mapper.TPayLogMapper;
import com.dilo.orderservice.service.TOrderService;
import com.dilo.orderservice.service.TPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dilo.orderservice.utils.HttpClient;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author dilo
 * @since 2022-02-23
 */
@Service
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements TPayLogService {

    @Autowired
    private TOrderService orderService;

    @Override
    public Map<String, Object> createNative(String orderNo) {
        try {
            //1.查询订单信息
            QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
            TOrder order = orderService.getOne(queryWrapper);

            //1.1.校验订单参数
            if (order == null){
                throw new DiloException(ResultCode.ERROR,"订单失效");
            }

            //2.封装支付参数
            HashMap<String,String> m = new HashMap();
            //设置支付参数
            m.put("appid", "wx74862e0dfcf69954");//应用id
            m.put("mch_id", "1558950191");//商户号
            m.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
            m.put("body", order.getCourseTitle());//课程名称
            m.put("out_trade_no", orderNo);//订单编号
            m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");//交易金额
            m.put("spbill_create_ip", "127.0.0.1");//终端ip
            m.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");//回调地址
            m.put("trade_type", "NATIVE");//交易类型


            //3.通过httpclient 发送请求,参数转化成xml
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");

            //3.1client设置参数
            client.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            //4.获取返回值

            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);


            //5.封装返回结果
            HashMap<String, Object> map = new HashMap<>();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));
            map.put("code_url", resultMap.get("code_url"));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DiloException(ResultCode.ERROR,"获取二维码失败");
        }
    }

    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        try {
            HashMap<String, String> map = new HashMap<>();

            //1.封装支付参数
            map.put("appid", "wx74862e0dfcf69954");
            map.put("mch_id", "1558950191");
            map.put("out_trade_no", orderNo);
            map.put("nonce_str", WXPayUtil.generateNonceStr());

            //2.设置请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(map,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.post();

            //3.返回第三方数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DiloException(ResultCode.ERROR,"查询订单状态失败");
        }
    }

    //支付成功后,更新订单状态
    @Override
    public void updateOrderStatus(Map<String, String> map) {
        //1.更新订单状态
        //1.1获取订单编号
        String orderNo = map.get("out_trade_no");
        //1.2根据订单编号,查询订单信息

        TOrder order = orderService.getOne(new QueryWrapper<TOrder>().eq("order_no", orderNo));

        //1.3更新订单状态
        if (order.getStatus().intValue() == 1){
            order.setStatus(1);
            orderService.updateById(order);
        }

        //2.记录支付记录
        TPayLog tPayLog = new TPayLog();
        tPayLog.setOrderNo(order.getOrderNo()); //支付订单号
        tPayLog.setPayTime(new Date());//支付日期
        tPayLog.setPayType(1);//支付类型
        tPayLog.setTotalFee(order.getTotalFee());//支付总金额
        tPayLog.setTradeState(map.get("trade_state"));//支付状态
        tPayLog.setTransactionId(map.get("transaction_id"));
        tPayLog.setAttr(JSONObject.toJSONString(map));

        baseMapper.insert(tPayLog);



    }
}
