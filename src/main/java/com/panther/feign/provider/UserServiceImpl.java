package com.panther.feign.provider;

import com.panther.base.BizResult;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: UserServiceImpl.java, 2024/7/22 15:07 $
 */
public class UserServiceImpl implements UserClient {
    @Override
    public BizResult<?> test1(String test1) {
        return BizResult.create("hello world!");
    }

    @Override
    public BizResult<?> test2(String test2) {
        return BizResult.create("hello world!");
    }
}
