package com.panther.mybatis.executor;


import com.panther.mybatis.mapping.BoundSql;
import com.panther.mybatis.mapping.MappedStatement;
import com.panther.mybatis.session.ResultHandler;
import com.panther.mybatis.session.RowBounds;
import com.panther.mybatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author panther
 * @Date 2022/5/11 19:25
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    int update(MappedStatement ms, Object parameter) throws SQLException;

    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql)throws SQLException ;

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

}
