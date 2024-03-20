package com.example.testredis.test;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author WangZhen
 * @Date 2024/3/15 23:54
 */
@Component
public class TestRedis {

    @Resource
    private RedisTemplate redisTemplate;



}
