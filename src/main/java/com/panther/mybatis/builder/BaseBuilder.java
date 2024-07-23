package com.panther.mybatis.builder;

import com.panther.mybatis.session.Configuration;
import com.panther.mybatis.type.TypeAliasRegistry;
import com.panther.mybatis.type.TypeHandlerRegistry;

/**
 * @Author panther
 * @Date 2022/4/21 14:54
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;
    protected final TypeHandlerRegistry typeHandlerRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
        this.typeHandlerRegistry = this.configuration.getTypeHandlerRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    protected Class<?> resolveAlias(String alias) {
        return typeAliasRegistry.resolveAlias(alias);
    }
}
