package com.atguigu;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

public class ConnectRedis {
    public static JedisPool pool =null ;
    public static Jedis getjedis(){
        if (pool==null){
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(10);
            jedisPoolConfig.setMaxIdle(5);
            jedisPoolConfig.setMinIdle(5);
            jedisPoolConfig.setBlockWhenExhausted(true);
            jedisPoolConfig.setMaxWaitMillis(50000);
            jedisPoolConfig.setTestOnBorrow(true);
            pool = new JedisPool(jedisPoolConfig,"hadoop102",6379);
        }
       return pool.getResource();

    }

    public static void main(String[] args) {
        Jedis jedis = getjedis();
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        String jinpm_type = jedis.type("jinpm");
        System.out.println(jinpm_type);
        String aa_type = jedis.type("aa");
        System.out.println(aa_type);
        String zz_type = jedis.type("zz");
        System.out.println(zz_type);
        String hget = jedis.hget("zz", "i");
        System.out.println(hget);
        jedis.shutdown();


    }
}
