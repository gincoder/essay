package com.panther.mybatis.session;

import com.panther.mybatis.builder.xml.XMLConfigBuilder;
import com.panther.mybatis.datasource.DataSourceFactory;
import com.panther.mybatis.mapping.Environment;
import com.panther.mybatis.session.defaults.DefaultSqlSessionFactory;
import com.panther.mybatis.transaction.TransactionFactory;
import com.panther.mybatis.type.TypeAliasRegistry;
import org.dom4j.Document;

import javax.sql.DataSource;
import java.io.Reader;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * SqlSessionFactoryBuilder 是作为整个 Mybatis 的入口类，通过指定解析XML的IO，引导整个流程的启动。
 * 从这个类开始新增加了 XMLConfigBuilder、Configuration 两个处理类，分别用于解析 XML 和串联整个流程的对象保存操作。接下来我们会分别介绍这些新引入的对象。
 *
 * @Author panther
 * @Date 2022/4/12 20:17
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader){
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration configuration){
        return new DefaultSqlSessionFactory(configuration);
    }

    public SqlSessionFactory build(Properties properties, List<Document> documentList) throws IllegalAccessException, InstantiationException {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.getConfiguration();
        TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
        // 事务管理器
        TransactionFactory txFactory = (TransactionFactory) typeAliasRegistry.resolveAlias(properties.getProperty("transactionManagerType")).newInstance();
        DataSourceFactory dataSourceFactory = (DataSourceFactory) typeAliasRegistry.resolveAlias(properties.getProperty("dataSourceType")).newInstance();
        dataSourceFactory.setProperties(properties);
        DataSource dataSource = dataSourceFactory.getDataSource();

        // 构建环境
        Environment.Builder environmentBuilder = new Environment.Builder("defaultEnvironment")
                .transactionFactory(txFactory)
                .dataSource(dataSource);
        configuration.setEnvironment(environmentBuilder.build());

        //添加mapper
        return build(xmlConfigBuilder.parseMapper(documentList));
    }
}
