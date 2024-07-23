package com.panther.mybatis.executor.resultset;

import com.panther.mybatis.mapping.ResultMap;
import com.panther.mybatis.session.Configuration;
import com.panther.mybatis.type.JdbcType;
import com.panther.mybatis.type.TypeHandler;
import com.panther.mybatis.type.TypeHandlerRegistry;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * @Author panther
 * @Date 2022/6/13 18:50
 */
public class ResultSetWrapper {

    private final ResultSet resultSet;
    private final List<String> columnNames = new ArrayList<>();
    private final List<String> classNames = new ArrayList<>();
    private final List<JdbcType> jdbcTypes = new ArrayList<>();
    private final TypeHandlerRegistry typeHandlerRegistry;
    private final Map<String, Map<Class<?>, TypeHandler<?>>> typeHandlerMap = new HashMap<>();
    private Map<String, List<String>> mappedColumnNamesMap = new HashMap<>();
    private Map<String, List<String>> unMappedColumnNamesMap = new HashMap<>();

    public ResultSetWrapper(ResultSet resultSet, Configuration configuration)  throws SQLException {
        this.resultSet = resultSet;
        typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        final ResultSetMetaData metaData = resultSet.getMetaData();
        //  返回此 ResultSet 对象中的列数。
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            //获取用于打印输出和显示的指定列的建议标题。
            columnNames.add(metaData.getColumnLabel(i));
            //java对应类型名称
            classNames.add(metaData.getColumnClassName(i));
            //检索指定列的 SQL 类型。
            jdbcTypes.add(JdbcType.forCode(metaData.getColumnType(i)));
        }
    }

    public TypeHandler<?> getTypeHandler(Class<?> propertyType, String columnName) {
        TypeHandler<?> handler = null;
        Map<Class<?>, TypeHandler<?>> columnHandlers = typeHandlerMap.get(columnName);
        if (columnHandlers == null) {
            columnHandlers = new HashMap<>();
            typeHandlerMap.put(columnName, columnHandlers);
        } else {
            handler = columnHandlers.get(propertyType);
        }
        if (handler == null) {
            handler = typeHandlerRegistry.getTypeHandler(propertyType, null);
            columnHandlers.put(propertyType, handler);
        }
        return handler;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public List<String> getUnmappedColumnNames(ResultMap resultMap, String columnPrefix) {
        List<String> unMappedColumnNames = unMappedColumnNamesMap.get(getMapKey(resultMap, columnPrefix));
        if (unMappedColumnNames == null || unMappedColumnNames.size() ==0){
            loadMappedAndUnmappedColumnNames(resultMap, columnPrefix);
            unMappedColumnNames = unMappedColumnNamesMap.get(getMapKey(resultMap, columnPrefix));
        }
        return unMappedColumnNames;
    }

    private void loadMappedAndUnmappedColumnNames(ResultMap resultMap, String columnPrefix) {

        String mapKey = getMapKey(resultMap, columnPrefix);
        List<String> mappedColumnNames = new ArrayList();
        List<String> unmappedColumnNames = new ArrayList();

        final String upperColumnPrefix = columnPrefix==null? null: columnPrefix.toUpperCase(Locale.ENGLISH);
        final Set<String> mappedColumns = setMappedColumns(resultMap.getMappedColumns(), upperColumnPrefix);

        for (String columnName : columnNames) {
            final String upperColumnName = columnName.toUpperCase(Locale.ENGLISH);
            if (mappedColumns.contains(upperColumnName)) {
                mappedColumnNames.add(upperColumnName);
            } else {
                unmappedColumnNames.add(columnName);
            }
        }


        mappedColumnNamesMap.put(mapKey,mappedColumnNames);
        unMappedColumnNamesMap.put(mapKey,unmappedColumnNames);
    }

    private Set<String> setMappedColumns(Set<String> mappedColumns, String columnPrefix) {

        if (mappedColumns == null || mappedColumns.size() == 0 || null == columnPrefix){
            return mappedColumns;
        }
        Set<String> columns = new HashSet<>();
        for (String mappedColumn : mappedColumns) {
            columns.add(columnPrefix+mappedColumn);
        }
        return columns;

    }

    public String getMapKey(ResultMap resultMap,String id){

        return resultMap.getId()+":"+id;
    }
}
