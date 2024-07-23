package com.panther.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class quick {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer()
                .setConnectionMinimumIdleSize(5) // 设置最下空闲数
                .setConnectionPoolSize(10) // 设置连接池大小
                .setIdleConnectionTimeout(3*1000) // 设置空闲连接超时时间 单位为毫秒
                .setConnectTimeout(10*1000) // 设置连接超时时间 单位为毫秒
                .setAddress("redis://127.0.0.1:6379")
                .setDatabase(0);
        //获取客户端
        RedissonClient redissonClient = Redisson.create(config);
        RBucket<String> selfBucket = redissonClient.getBucket("selfBucket");
        selfBucket.set("hello",3, TimeUnit.SECONDS);
        selfBucket.set("world");
        //获取所有的key
        redissonClient.getKeys().getKeys().forEach(key -> System.out.println(key));
        //关闭客户端
        redissonClient.shutdown();
    }

}
