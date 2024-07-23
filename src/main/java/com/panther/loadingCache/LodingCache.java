package com.panther.loadingCache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: LodingCache.java, 2024/7/22 15:37 $
 */
public class LodingCache {

    public static void RebuildCache() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100) // 最大缓存项数
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return "Hello, " + key; // 定义缓存加载的方式
                    }
                });
        System.out.println(cache.getUnchecked("Guava")); // 输出：Hello, Guava
    }

}
