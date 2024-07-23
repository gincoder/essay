package com.panther.mybatis.scripting.xmltags;

/**
 * @description SQL 节点
 */
public interface SqlNode {

    boolean apply(DynamicContext context);

}