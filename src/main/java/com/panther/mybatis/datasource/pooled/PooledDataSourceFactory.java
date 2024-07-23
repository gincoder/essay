package com.panther.mybatis.datasource.pooled;



import com.panther.mybatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @Author panther
 * @Date 2022/5/3 13:17
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }
}
