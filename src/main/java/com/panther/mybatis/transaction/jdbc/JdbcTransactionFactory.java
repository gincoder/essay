package com.panther.mybatis.transaction.jdbc;

import com.panther.mybatis.session.TransactionIsolationLevel;
import com.panther.mybatis.transaction.Transaction;
import com.panther.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @Author panther
 * @Date 2022/4/27 20:13
 */
public class JdbcTransactionFactory implements TransactionFactory {

    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
