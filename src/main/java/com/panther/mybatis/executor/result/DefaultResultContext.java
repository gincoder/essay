package com.panther.mybatis.executor.result;

import com.panther.mybatis.session.ResultContext;

/**
 * @Author panther
 * @Date 2022/6/14 9:47
 */
public class DefaultResultContext implements ResultContext {

    private Integer resultCount;

    private Object resultObject;

    public DefaultResultContext() {
        this.resultObject = null;
        this.resultCount = 0;
    }

    @Override
    public Object getResultObject() {
        return resultObject;
    }

    @Override
    public int getResultCount() {
        return resultCount;
    }

    public void nextResultObject(Object resultObject) {
        this.resultObject = resultObject;
        resultCount++;
    }
}
