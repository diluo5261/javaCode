package com.dilo.springboot.service.impl;

import com.dilo.springboot.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public String sayHello(String message) {
        return "hello"+message;
    }
}
