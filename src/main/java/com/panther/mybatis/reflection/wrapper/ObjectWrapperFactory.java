package com.panther.mybatis.reflection.wrapper;

import com.panther.mybatis.reflection.MetaObject;

/**
 * @Author panther
 * @Date 2022/6/2 16:18
 */
public interface ObjectWrapperFactory {
    /**
     * 判断有没有包装器
     */
    boolean hasWrapperFor(Object object);

    /**
     * 得到包装器
     */
    ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);
}
