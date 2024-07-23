package com.panther.loadingCache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class AutoRefreshCache {
    public static void main(String[] args) throws Exception {
        LoadingCache<String, String> cache = CacheBuilder
                .newBuilder()
                .refreshAfterWrite(5, TimeUnit.SECONDS) // 设置5s后刷新 更新缓存
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return fetchDataFromDatabase(key); // 模拟从数据库加载数据
                    }
                });
        // 使用缓存
        System.out.println(cache.get("key1")); // 第一次加载
        // 5s钟后，尝试再次获取，将触发刷新操作
        TimeUnit.SECONDS.sleep(5);
        System.out.println(cache.get("key1")); // 第二次加载

    }

    private static String fetchDataFromDatabase(String key) {
        // 模拟数据库操作
        return "Data for " + key;
    }
}