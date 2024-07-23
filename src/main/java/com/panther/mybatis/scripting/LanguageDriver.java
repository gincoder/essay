package com.panther.mybatis.scripting;


import com.panther.mybatis.executor.parameter.ParameterHandler;
import com.panther.mybatis.mapping.BoundSql;
import com.panther.mybatis.mapping.MappedStatement;
import com.panther.mybatis.mapping.SqlSource;
import com.panther.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * @description 脚本语言驱动
 */
public interface LanguageDriver {

    /**
     * 创建SQL源码(mapper xml方式)
     */
    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

    /**
     * 创建参数处理器
     */
    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);

}
