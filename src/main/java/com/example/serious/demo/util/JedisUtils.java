package com.example.serious.demo.util;

import redis.clients.jedis.Jedis;
public class JedisUtils {

        Jedis jedis;

        private JedisUtils(String url){
            this.jedis = new Jedis(url);
        }
        public static Jedis getInstance(String url){
            JedisUtils jedisUtils = new JedisUtils(url);
            jedisUtils.jedis.ping("Redis连接成功........");
            return jedisUtils.jedis;
        }
}
