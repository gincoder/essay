package com.panther.mybatis.reflection.invoker;

/**
 * @description 调用者
 */
public interface Invoker {

    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();

}
