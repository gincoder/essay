package com.panther.mybatis.reflection;

import com.panther.mybatis.reflection.factory.DefaultObjectFactory;
import com.panther.mybatis.reflection.factory.ObjectFactory;
import com.panther.mybatis.reflection.wrapper.DefaultObjectWrapperFactory;
import com.panther.mybatis.reflection.wrapper.ObjectWrapperFactory;

/**
 * @Author panther
 * @Date 2022/6/2 16:12
 */
public class SystemMetaObject {

    public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    public static final MetaObject NULL_META_OBJECT = MetaObject.forObject(NullObject.class, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);

    public static MetaObject forObject(Object object) {
        return MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
    }

    /**
     * 空对象
     */
    private static class NullObject {
    }
}
