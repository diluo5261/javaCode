package com.dilo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @RequestMapping("/find")
    public String findById(){
        System.out.println("springboot2 is running... ");
        return "springboot2 is running... ";
    }
}
