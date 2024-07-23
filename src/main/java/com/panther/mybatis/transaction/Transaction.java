package com.panther.mybatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author panther
 * @Date 2022/4/27 19:25
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;

}
