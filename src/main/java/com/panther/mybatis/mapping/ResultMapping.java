package com.panther.mybatis.mapping;

import com.panther.mybatis.session.Configuration;
import com.panther.mybatis.type.JdbcType;
import com.panther.mybatis.type.TypeHandler;

/**
 * @Author panther
 * @Date 2022/6/13 16:42
 */
public class ResultMapping {

    private Configuration configuration;
    private String property;
    private String column;
    private Class<?> javaType;
    private JdbcType jdbcType;
    private TypeHandler<?> typeHandler;

    ResultMapping() {
    }

    public static class Builder {
        private ResultMapping resultMapping = new ResultMapping();


    }

}
