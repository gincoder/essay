package com.panther.mybatis.scripting.xmltags;


import com.panther.mybatis.executor.parameter.ParameterHandler;
import com.panther.mybatis.mapping.BoundSql;
import com.panther.mybatis.mapping.MappedStatement;
import com.panther.mybatis.mapping.SqlSource;
import com.panther.mybatis.scripting.LanguageDriver;
import com.panther.mybatis.scripting.defaults.DefaultParameterHandler;
import com.panther.mybatis.session.Configuration;
import org.dom4j.Element;

/**
 * @description XML语言驱动器

 */
public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
    }

}