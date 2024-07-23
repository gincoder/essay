package com.panther.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.panther.caffeine.properties.CaffeineProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Caffeine配置
 *
 * @author AntonyCheng
 */
@Configuration
@EnableConfigurationProperties(CaffeineProperties.class)
@AllArgsConstructor
@Slf4j
public class CaffeineConfiguration {

    private final CaffeineProperties caffeineProperties;

    @Bean("localCache")
    public Cache<String, Object> localCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(caffeineProperties.getExpired(), TimeUnit.SECONDS)
                .expireAfterAccess(caffeineProperties.getExpired(), TimeUnit.SECONDS)
                // 初始的缓存空间大小
                .initialCapacity(caffeineProperties.getInitCapacity())
                // 缓存的最大条数
                .maximumSize(caffeineProperties.getMaxCapacity())
                //监听缓存被移除
                .removalListener((key, val, removalCause) -> {
                   log.info("键{}被移除,移除原因{}",key,removalCause);
                })
                .build();
    }
}