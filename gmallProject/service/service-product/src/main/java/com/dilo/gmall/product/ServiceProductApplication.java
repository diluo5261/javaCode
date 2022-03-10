package com.dilo.gmall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan({"com.dilo.gmall"})
@EnableDiscoveryClient
//@CrossOrigin
public class ServiceProductApplication {

   public static void main(String[] args) {
      SpringApplication.run(ServiceProductApplication.class, args);
   }
}
