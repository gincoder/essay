package com.panther.mybatis.executor.result;

import com.panther.mybatis.reflection.factory.ObjectFactory;
import com.panther.mybatis.session.ResultContext;
import com.panther.mybatis.session.ResultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author panther
 * @Date 2022/6/13 19:35
 */
public class DefaultResultHandler implements ResultHandler {

    private final List<Object> list;

    public DefaultResultHandler() {
        this.list = new ArrayList<>();
    }

    /**
     * 通过 ObjectFactory 反射工具类，产生特定的 List
     */
    @SuppressWarnings("unchecked")
    public DefaultResultHandler(ObjectFactory objectFactory) {
        this.list = objectFactory.create(List.class);
    }

    @Override
    public void handleResult(ResultContext context) {
        list.add(context.getResultObject());
    }

    public List<Object> getResultList() {
        return list;
    }

}
