package com.dilo.springboot;

import com.dilo.springboot.service.SendService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RabbitmqSpringbootProducerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RabbitmqSpringbootProducerApplication.class, args);
        SendService service = context.getBean("sendService", SendService.class);
        service.sendMessage("boot测试数据");
    }

}
