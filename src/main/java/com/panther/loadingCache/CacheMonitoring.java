package com.panther.loadingCache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;

import java.util.concurrent.ExecutionException;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: CacheMonitoring.java, 2024/7/22 15:35 $
 */
public class CacheMonitoring {

    public static void CacheNotice() throws ExecutionException {
        // 监听失效缓存，打印原因
        RemovalListener<String, String> removalListener = notification -> {
            System.out.println("Removed: " + notification.getKey() + ", Cause: " + notification.getCause());
        };

        LoadingCache<String, String> cache = CacheBuilder
                .newBuilder()
                .maximumSize(100)
                .removalListener(removalListener)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return key;
                    }
                });
        String value  = cache.get("key");
        System.out.println(value);
        cache.cleanUp();

    }

}
