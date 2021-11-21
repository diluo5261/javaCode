package com.dilo.springboot;

import com.dilo.springboot.service.ConsumerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RabbitmqSpringbootConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RabbitmqSpringbootConsumerApplication.class, args);
        //ConsumerService service = context.getBean("consumerService", ConsumerService.class);
        //service.receive();
    }

}
