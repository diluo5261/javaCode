package com.dilo.springboot.service.impl;

import com.dilo.springboot.service.StudentService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public String sayHello() {
        return "hello";
    }
}
