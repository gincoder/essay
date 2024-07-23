package com.panther.lock;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: redisLock.java, 2024/7/12 14:22 $
 */
public class redisLock {

    private RedisTemplate redisTemplate;

    public boolean lock(String key){

        long startTime = System.currentTimeMillis();

        return Boolean.TRUE;
    }

}
