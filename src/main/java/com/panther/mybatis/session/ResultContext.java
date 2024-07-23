package com.panther.mybatis.session;

/**
 * @Author panther
 * @Date 2022/6/14 9:47
 */
public interface ResultContext {

    /**
     * 获取结果
     */
    Object getResultObject();

    /**
     * 获取记录数
     */
    int getResultCount();

}
