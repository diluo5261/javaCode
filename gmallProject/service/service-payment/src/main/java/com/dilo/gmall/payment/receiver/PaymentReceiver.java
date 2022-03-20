package com.dilo.gmall.payment.receiver;

import com.dilo.gmall.common.constant.MqConst;
import com.dilo.gmall.payment.service.PaymentService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PaymentReceiver {
    @Autowired
    private PaymentService paymentService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_PAYMENT_CLOSE,durable = "true"),
            exchange = @Exchange(value = MqConst.EXCHANGE_DIRECT_PAYMENT_CLOSE),
            key = {MqConst.ROUTING_PAYMENT_CLOSE}
    ))
    public void closePayment(Long orderId , Message message, Channel channel) throws IOException {
        if (null != orderId){
            // 关闭交易
            paymentService.closePayment(orderId);
        }
        // 手动ack
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


}
