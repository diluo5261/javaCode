package com.dilo.gmall.order.receiver;

import com.alibaba.fastjson.JSON;
import com.dilo.gmall.common.constant.MqConst;
import com.dilo.gmall.model.enums.OrderStatus;
import com.dilo.gmall.model.enums.ProcessStatus;
import com.dilo.gmall.model.order.OrderInfo;
import com.dilo.gmall.model.payment.PaymentInfo;
import com.dilo.gmall.order.service.OrderService;
import com.dilo.gmall.payment.client.PaymentFeignClient;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;


@Component
public class OrderReceiver {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentFeignClient paymentFeignClient;

    //第一种:绑定
    //使用延迟消息:制作配置类:直接监听队列
    @RabbitListener(queues = MqConst.QUEUE_ORDER_CANCEL)
    public void cancelOrder(Long orderId, Message message, Channel channel) throws IOException {

        //关闭orderInfo,paymentInfo,支付宝交易记录
        //取消订单的业务逻辑
        if (orderId != null) {
            //根据orderid查询订单对象
            OrderInfo orderInfo = orderService.getById(orderId);

            //判断状态
            if (orderId != null && "UNPAID".equals(orderInfo.getOrderStatus())) {
                //是否存在电商交易记录
                PaymentInfo paymentInfo = paymentFeignClient.getPaymentInfo(orderInfo.getOutTradeNo());

                if (paymentInfo != null && "UNPAID".equals(paymentInfo.getPaymentStatus())){
                    //说明电商本地有交易记录

                    //调用支付宝的交易记录
                    Boolean result = paymentFeignClient.checkPayment(orderId);
                    if (result){
                        //判断是否能够关闭支付宝交易
                        Boolean flag = paymentFeignClient.closePay(orderId);
                        if (flag){
                            //表示未支付
                            orderService.execExpiredOrder(orderId,"2");
                        }else{
                            //表示支付成功
                            //用户正常支付成功了
                        }

                    }else{
                        //支付宝没有加依记录,
                        orderService.execExpiredOrder(orderId,"2");
                    }

                }else{
                    //修改订单状态CLOSED
                    //execExpiredOrder这个方法有关闭orderInfo,还有关闭paymentInfo
                    orderService.execExpiredOrder(orderId,"1");
                }
            }
        }
        //手动确认消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_PAYMENT_PAY, durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = MqConst.EXCHANGE_DIRECT_PAYMENT_PAY),
            key = {MqConst.ROUTING_PAYMENT_PAY}
    ))
    public void paymentPay(Long orderId, Message message, Channel channel) throws IOException {

        //判断
        if (orderId != null) {
            OrderInfo orderInfo = orderService.getById(orderId);

            if (null != orderInfo && "UNPAID".equals(orderInfo.getOrderStatus()) && "UNPAID".equals(orderInfo.getProcessStatus())) {
                //更新状态,PAID
                orderService.updateOrderStatus(orderId, ProcessStatus.PAID);
                //发送消息给库存,通知仓库减少数量,
                orderService.sendOrderStatus(orderId);

            }
        }
        //手动确认
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

    }

    //禁停库存发送的减库存结果
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_WARE_ORDER, durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = MqConst.EXCHANGE_DIRECT_WARE_ORDER),
            key = {MqConst.ROUTING_WARE_ORDER}
    ))
    public void updateOrderStatus(String wareJson, Message message, Channel channel) {
        if (!StringUtils.isEmpty(wareJson)) {
            //wareJson转换成map
            Map map = JSON.parseObject(wareJson, Map.class);
            String orderId = (String) map.get("orderId");
            String status = (String) map.get("status");

            if ("DEDUCTED".equals(status)) {
                //减库存成功,更新订单状态
                orderService.updateOrderStatus(Long.parseLong(orderId),ProcessStatus.WAITING_DELEVER);
            }else{
                //异常情况下:1.远程调用补货!2.人工客服小姐姐
                orderService.updateOrderStatus(Long.parseLong(orderId),ProcessStatus.STOCK_EXCEPTION);
                //订单---支付---库存!可以看作是一个分布式事务!mq解决!最终一致性
            }
        }
    }
}
