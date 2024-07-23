package com.panther.mybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Author panther
 * @Date 2022/4/28 14:50
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();
}
