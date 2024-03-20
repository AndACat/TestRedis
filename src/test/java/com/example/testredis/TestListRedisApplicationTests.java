package com.example.testredis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.List;

/**
 * @author WangZhen
 * @Date 2024/3/20 16:03
 */
@SpringBootTest
public class TestListRedisApplicationTests {
    @Autowired
    private RedisTemplate<String, List> redisTemplate;

    @BeforeEach
    void cls(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }
    @AfterEach
    void addRow(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");

    }

    @Test
    void test1(){

    }
}
