package com.dilo.gmall.task.scheduled;


import com.dilo.gmall.common.constant.MqConst;
import com.dilo.gmall.common.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTask {

    @Autowired
    private RabbitService rabbitService;

    /*
       每天凌晨1点执行
     */
    @Scheduled(cron = "0/10 * * * * ?")
    //@Scheduled(cron = "0 0 1 * * ?")
    //编写一个定时任务
    public void  sendMsg(){

        //rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_TASK,MqConst.ROUTING_TASK_1,"测试数据,发个寂寞,有数据就行");
        System.out.println("测试数据,发个寂寞,有数据就行");

    }


}
