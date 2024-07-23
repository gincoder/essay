package com.panther.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import com.panther.mybatis.session.Configuration;
import com.panther.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author panther
 * @Date 2022/4/6 14:45
 */
public class MapperRegistry {

    /**
     * 将已添加的映射器代理加入到 HashMap
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap();

    private Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {

        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }

    }

    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (knownMappers.get(type) != null) {
                return;
            }
            knownMappers.put(type, new MapperProxyFactory<T>(type));
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public <T> void addMappers(String packageName) {
        Set<Class<?>> classes = ClassScanner.scanPackage(packageName);
        for (Class type : classes) {
            addMapper(type);
        }
    }
}
