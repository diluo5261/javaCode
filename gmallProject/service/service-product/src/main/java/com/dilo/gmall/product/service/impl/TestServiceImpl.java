package com.dilo.gmall.product.service.impl;

import com.dilo.gmall.product.service.TestService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;



    @Override
    public  void testLock() {
        /*
        1.在缓存中存储一个初始值num为0
        2.利用缓存的StringRedisTemplate获取当前的值
        3.如果num不为空,进行+1操作
        4.如果为空,返回即可
         */

        RLock lock = redissonClient.getLock("lock");


            lock.lock(10L,TimeUnit.SECONDS);
            String num = redisTemplate.opsForValue().get("num");
            if (num == null) {
                return;
            }
            //不为空,加一后写回缓存
            redisTemplate.opsForValue().set("num", String.valueOf(Integer.parseInt(num) + 1));

            lock.unlock();

    }

    /*//3.设置过期时间
    @Override
    public  void testLock() {
        *//*
        1.在缓存中存储一个初始值num为0
        2.利用缓存的StringRedisTemplate获取当前的值
        3.如果num不为空,进行+1操作
        4.如果为空,返回即可
         *//*

        //Boolean flag = redisTemplate.opsForValue().setIfAbsent("lock", "ok");
        //4.利用UUID防止误删除
        String uuid = UUID.randomUUID().toString();
        Boolean flag = redisTemplate.opsForValue().setIfAbsent("lock",uuid,10, TimeUnit.SECONDS);

        //判断是否获取到锁
        if (flag) {
            //如果falg为true,表示获取到锁,执行业务逻辑

            String num = redisTemplate.opsForValue().get("num");
            if (num == null) {
                return;
            }
            //写回缓存
            redisTemplate.opsForValue().set("num", String.valueOf(Integer.parseInt(num) + 1));

            //如果缓存中的uuid与当前的uuid相同,则删除
            *//*String lock = redisTemplate.opsForValue().get("lock");
            if (uuid.equals(lock)){
                //释放锁
                redisTemplate.delete("lock");
            }
*//*
            //5.使用LUA脚本原子性删除
            //5.1.定义一个lua脚本
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptText(script);
            redisScript.setResultType(Long.class);

            redisTemplate.execute(redisScript, Arrays.asList("lock"),uuid);
        }else{
            try {
                //    没有获取到锁,等待
                Thread.sleep(500);
                //自旋
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/



    //2.不设置过期时间
    /*@Override
    public  void testLock() {
        Boolean flag = redisTemplate.opsForValue().setIfAbsent("lock", "ok");
        //判断是否获取到锁
        if (flag) {
            //如果falg为true,表示获取到锁,执行业务逻辑

            String num = redisTemplate.opsForValue().get("num");
            if (num == null) {
                return;
            }
            //写回缓存
            redisTemplate.opsForValue().set("num", String.valueOf(Integer.parseInt(num) + 1));

            //释放锁
            redisTemplate.delete("lock");
        }else{
            try {
                //    没有获取到锁,等待
                Thread.sleep(500);
                //自旋
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/


    /*
    1.本地锁实现方式,分布式时不适用
     */
    //@Override
    //public synchronized void testLock() {
    //    /*
    //    1.在缓存中存储一个初始值num为0
    //    2.利用缓存的StringRedisTemplate获取当前的值
    //    3.如果num不为空,进行+1操作
    //    4.如果为空,返回即可
    //     */
    //
    //    String num = redisTemplate.opsForValue().get("num");
    //    if (num == null){
    //        return ;
    //    }
    //
    //    //写回缓存
    //    redisTemplate.opsForValue().set("num",String.valueOf(Integer.parseInt(num)+1));
    //}
}
