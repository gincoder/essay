package com.panther.mybatis.binding;

import com.panther.mybatis.session.SqlSession;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author panther
 * @Date 2022/3/31 20:29
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    private Map<Method, MapperMethod> methodCache = new ConcurrentHashMap<Method, MapperMethod>();

    public MapperProxyFactory(Class<T> mapperInterface){
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession){
        MapperProxy<T> mapperProxy = new MapperProxy(sqlSession, mapperInterface,methodCache);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface}, mapperProxy);
    }

    public Map<Method, MapperMethod> getMethodCache() {
        return methodCache;
    }
}
