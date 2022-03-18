package com.dilo.gmall.order.receiver;

import com.dilo.gmall.common.constant.MqConst;
import com.dilo.gmall.model.enums.OrderStatus;
import com.dilo.gmall.model.enums.ProcessStatus;
import com.dilo.gmall.model.order.OrderInfo;
import com.dilo.gmall.order.service.OrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.channels.Channel;

@Component
public class OrderReceiver {
    @Autowired
    private OrderService orderService;

    //第一种:绑定
    //使用延迟消息:制作配置类:直接监听队列
    @RabbitListener(queues = MqConst.QUEUE_ORDER_CANCEL)
    public void cancelOrder(Long orderId , Message message, Channel channel){
        //取消订单的业务逻辑
        if (orderId != null){
            //根据orderid查询订单对象
            OrderInfo orderInfo = orderService.getById(orderId);

            //判断状态
            if (orderId != null && "UNPAID".equals(orderInfo.getOrderStatus())){
                //修改订单状态CLOSED
                orderInfo.setOrderStatus(OrderStatus.CLOSED.name());
                orderInfo.setProcessStatus(ProcessStatus.CLOSED.name());

                orderService.updateById(orderInfo);

            }

        }

    }
}
