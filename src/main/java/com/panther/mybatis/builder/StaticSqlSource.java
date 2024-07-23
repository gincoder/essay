package com.panther.mybatis.builder;


import com.panther.mybatis.mapping.BoundSql;
import com.panther.mybatis.mapping.ParameterMapping;
import com.panther.mybatis.mapping.SqlSource;
import com.panther.mybatis.session.Configuration;

import java.util.List;

/**
 * @description 静态SQL源码
 */
public class StaticSqlSource implements SqlSource {

    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Configuration configuration;

    public StaticSqlSource(Configuration configuration, String sql) {
        this(configuration, sql, null);
    }

    public StaticSqlSource(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.configuration = configuration;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(configuration, sql, parameterMappings, parameterObject);
    }

}
