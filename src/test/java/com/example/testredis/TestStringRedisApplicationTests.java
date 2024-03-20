package com.example.testredis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class TestStringRedisApplicationTests {

    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @BeforeEach
    void cls(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }
    @AfterEach
    void addRow(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");

    }

    @Test
    void test1() {
        // 设置最简单的get set
        // 设置value
        redisTemplate.opsForValue().set("name", "张三");
        // 获取name的值，并打印出来
        String s = redisTemplate.opsForValue().get("name");
        System.out.println("s = " + s);
    }

    @Test
    void test2(){
        // 设置带过期时间的get和set
        redisTemplate.opsForValue().set("during_string1", "123123", Duration.ofHours(1));
        redisTemplate.opsForValue().set("during_string2", "123123", 30, TimeUnit.SECONDS);

        // 使用Thread的sleep睡眠1000毫秒
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 打印两个key的剩余存活时间
        Long duringString1 = redisTemplate.getExpire("during_string1");
        System.out.println("duringString1 = " + duringString1);
        Long duringString2 = redisTemplate.getExpire("during_string2");
        System.out.println("duringString2 = " + duringString2);
    }

    @Test
    void test3() throws InterruptedException {
        // setIfPresent  如果存在就设置值
        Boolean b = redisTemplate.opsForValue().setIfPresent("test3", "6666");
        System.out.println("b = " + b);

        // boundValueOps实际上是先绑定key, 再去操作其值，是操作方式的不同，实际上和opsForValue()相差无几
        redisTemplate.boundValueOps("test3").set("7777");
        String s = redisTemplate.boundValueOps("test3").get();
        System.out.println("s = " + s);
        redisTemplate.boundValueOps("test3").setIfPresent("88888", 30, TimeUnit.SECONDS);
        Thread.sleep(1890);
        Long test3 = redisTemplate.getExpire("test3");
        System.out.println("test3 = " + test3);
        String s1 = redisTemplate.opsForValue().get("test3");
        System.out.println("s1 = " + s1);

    }

    @Test
    void test4(){
        // setIfAbsent 如果缺少其值就设置，如果已经有值，就不设置了
        redisTemplate.opsForValue().set("test4", "666");
        String s = redisTemplate.opsForValue().get("test4");
        System.out.println("s = " + s);
        Boolean b = redisTemplate.opsForValue().setIfAbsent("test4", "1233433");
        System.out.println("b = " + b);
        String s1 = redisTemplate.opsForValue().get("test4");
        System.out.println("s1 = " + s1);

    }
    
    @Test
    void test5(){
        // string中bit相关的
        redisTemplate.opsForValue().set("test5", "88888888888888888888888");
        // 从指定偏移量开始覆盖key处存储的字符串的一部分，直至value的整个长度。如果偏移量大于key处字符串的当前长度，
        // 则用零字节填充该字符串以使偏移量适合。不存在的键被视为空字符串，
        // 因此此命令将确保它保存一个足够大的字符串，以便能够在offset处设置值。
        redisTemplate.opsForValue().set("test5", "567", 4);
        String s = redisTemplate.opsForValue().get("test5");
        System.out.println("s = " + s);
    }

    @Test
    void test6(){
        redisTemplate.opsForValue().set("test6", "56789");
        // 自增100个单位
        redisTemplate.opsForValue().increment("test6", 100);
        String s = redisTemplate.opsForValue().get("test6");
        System.out.println("s = " + s);
        
        // 自增1个单位
        redisTemplate.opsForValue().increment("test6");
        String s1 = redisTemplate.opsForValue().get("test6");
        System.out.println("s1 = " + s1);

        // 自减100个单位
        redisTemplate.opsForValue().decrement("test6", 100);
        String s2 = redisTemplate.opsForValue().get("test6");
        System.out.println("s2 = " + s2);
        
        // 自增浮点数个单位
        redisTemplate.opsForValue().increment("test6", 1.5f);
        String s3 = redisTemplate.opsForValue().get("test6");
        System.out.println("s3 = " + s3);
    }
    
    @Test
    void test7(){
        redisTemplate.opsForValue().set("test7", "66666666666666");
        // 获取key对应value的长度
        Long test7 = redisTemplate.opsForValue().size("test7");
        System.out.println("test7 = " + test7);
    }

    @Test
    void test8(){
        redisTemplate.opsForValue().set("test8", "张三数学考了");
        // 字符串拼接
        redisTemplate.opsForValue().append("test8", "99分");

        String s = redisTemplate.opsForValue().get("test8");
        System.out.println("s = " + s);
    }
















}
