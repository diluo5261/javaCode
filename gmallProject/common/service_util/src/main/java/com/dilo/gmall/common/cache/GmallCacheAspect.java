package com.dilo.gmall.common.cache;

import com.alibaba.fastjson.JSON;
import com.dilo.gmall.common.constant.RedisConst;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class GmallCacheAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;
    @Around("@annotation(com.dilo.gmall.common.cache.GmallCache)")
    public Object gmallCacheGetData(ProceedingJoinPoint joinPoint){

        /*1.获取方法上的注解
        2.获取到注解的前缀,组成缓存的key
        3.根据key获取缓存中的数据
        4.判断是否获取到了数据(分布式锁的业务逻辑)*/



        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //拿到方法上的注解
        GmallCache gmallCache = signature.getMethod().getAnnotation(GmallCache.class);

        //获取注解的前缀,注解使用在方法上
        String prefix = gmallCache.prefix();

        //获取到方法中的参数
        Object[] args = joinPoint.getArgs();

        //定义缓存的key,前缀+参数
        String key = prefix + Arrays.asList(args).toString();

        try {
            //从缓存中获取数据
            Object object = cacheHit(key,signature);

            //判断数据是否为空
            if (object == null){
                //1.为空,说明缓存中没有数据,需要想数据库中查找信息

                //1.1先加锁
                String lockKey = key + ":lock";

                //1.2.上锁
                RLock lock = redissonClient.getLock(lockKey);
                boolean res = lock.tryLock(RedisConst.SKULOCK_EXPIRE_PX1, RedisConst.SKULOCK_EXPIRE_PX2, TimeUnit.SECONDS);
                //获取锁对象
                if (res){
                    //上锁成功,执行@GmallCache注解中表示的方法
                    try{
                        object = joinPoint.proceed(args);

                        //判断,防止缓存穿透
                        if (object == null){
                            Object object2 = new Object();
                            redisTemplate.opsForValue().set(key, JSON.toJSONString(object2),RedisConst.SKUKEY_TIMEOUT,TimeUnit.SECONDS);
                            return object2;
                        }
                        //不为空,skuInfo不为空
                        //将数据传入缓存并返回
                        redisTemplate.opsForValue().set(key, JSON.toJSONString(object),RedisConst.SKUKEY_TIMEOUT,TimeUnit.SECONDS);

                        //返回数据
                        return object;
                    }finally {
                        //解锁
                        lock.unlock();
                    }
                }else{
                    //没有获取到锁对象
                    //谁几秒钟,自旋
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return this.gmallCacheGetData(joinPoint);
                }
            }else{
                //缓存中有了,直接返回
                return object;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        //兜底方法,返回数据库数据
        Object proceed = null;
        try {
            proceed = joinPoint.proceed(args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return proceed;

    }

    private Object cacheHit(String key, MethodSignature signature) {
        String sObject = (String) redisTemplate.opsForValue().get(key);
        if (sObject != null){
            //  返回数据！ 获取到返回类型
            //  如果缓存 ： public BigDecimal getSkuPrice(Long skuId) 返回值 BigDecimal
            //  如果缓存：  public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId) 返回SpuSaleAttr
            //  如果缓存：  public SkuInfo getSkuInfo(Long skuId) 返回SkuInfo

            //从signature中获取返回值的类型
            Class returnType = signature.getReturnType();

            //将字符串变为要转换的数据类型
            return JSON.parseObject(sObject,returnType);
        }
        return null;
    }
}
