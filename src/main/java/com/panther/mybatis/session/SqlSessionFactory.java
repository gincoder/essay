package com.panther.mybatis.session;

/**
 * @Author panther
 * @Date 2022/4/6 14:46
 */
public interface SqlSessionFactory {

    /**
     * 打开一个 session
     * @return SqlSession
     */
    SqlSession openSession();
}
