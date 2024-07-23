package com.panther;

import org.redisson.api.RBitSet;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableConfigurationProperties
// @EnableFeignClients
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);

        RedissonClient redis = run.getBean(RedissonClient.class);
        RBitSet bitSet = redis.getBitSet("sign");
        bitSet.set(0);

    }


}
