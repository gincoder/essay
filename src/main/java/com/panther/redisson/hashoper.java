package com.panther.redisson;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class hashoper {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setDatabase(0);
        //获取客户端
        RedissonClient redissonClient = Redisson.create(config);
        RBloomFilter rBloomFilter = redissonClient.getBloomFilter("seqId");
// 初始化预期插入的数据量为10000和期望误差率为0.01
        rBloomFilter.tryInit(10000, 0.01);
// 插入部分数据
        rBloomFilter.add("100");
        rBloomFilter.add("200");
        rBloomFilter.add("300");
//设置过期时间
        rBloomFilter.expire(30, TimeUnit.SECONDS);
// 判断是否存在
        System.out.println(rBloomFilter.contains("300"));
        System.out.println(rBloomFilter.contains("200"));
        System.out.println(rBloomFilter.contains("999"));
    }

}
