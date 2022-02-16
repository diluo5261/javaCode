package com.dilo.jedis;

import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args) {
        //连接redis ,IP 地址  和 端口号
        Jedis jedis = new Jedis("192.168.169.128",6379);
        String ret = jedis.ping();
        System.out.println(ret);

        //System.out.println(jedis.get("k1"));

    }

}
