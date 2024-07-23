package com.panther.mybatis.session.defaults;

import com.panther.mybatis.executor.Executor;
import com.panther.mybatis.mapping.Environment;
import com.panther.mybatis.session.Configuration;
import com.panther.mybatis.session.SqlSession;
import com.panther.mybatis.session.SqlSessionFactory;
import com.panther.mybatis.session.TransactionIsolationLevel;
import com.panther.mybatis.transaction.Transaction;
import com.panther.mybatis.transaction.TransactionFactory;

import java.sql.SQLException;

/**
 * @Author panther
 * @Date 2022/4/6 14:47
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            // 创建DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }

}
