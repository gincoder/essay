package com.panther.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class StringOper {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setDatabase(0);
        //获取客户端
        RedissonClient redissonClient = Redisson.create(config);
        //字符串操作
        RLock lock = redissonClient.getLock("lock");
        RBucket<String> rBucket = redissonClient.getBucket("strKey2");
        System.out.println(redissonClient.getBucket("strKey2").get());
    }

}
