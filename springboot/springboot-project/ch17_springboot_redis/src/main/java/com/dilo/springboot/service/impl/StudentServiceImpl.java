package com.dilo.springboot.service.impl;

import com.dilo.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class StudentServiceImpl implements StudentService {

    /**
     * redis模板对象,用于操作redis的,
     * key和value都指定为 Object
     */
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public void put(String key, String value) {
        redisTemplate.opsForValue().set(key,value);

    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);

    }
}
