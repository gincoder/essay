package com.panther.mybatis.datasource.pooled;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author panther
 * @Date 2022/5/3 13:21
 */
public class PoolState {
    //数据源对象
    protected PooledDataSource dataSource;
    //空闲连接
    protected final List<PooledConnection> idleConnections = new ArrayList<>();
    //活跃连接
    protected final List<PooledConnection> activeConnections = new ArrayList<>();
    //请求次数
    protected long requestCount = 0 ;
    //累计请求时间
    protected long accumulatedRequestTime = 0;
    //累计等待时间
    protected long accumulatedCheckoutTime = 0;
    //声明的过期连接计数
    protected long claimedOverdueConnectionCount = 0;
    //累计的过期连接的等待时间
    protected long accumulatedCheckoutTimeOfOverdueConnections = 0;
    // 总等待时间
    protected long accumulatedWaitTime = 0;
    // 要等待的次数
    protected long hadToWaitCount = 0;
    //错误的连接计数
    protected long badConnectionCount = 0;

    public PoolState(PooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public synchronized long getRequestCount() {
        return requestCount;
    }
    //平均请求时间
    public synchronized long getAverageRequestTime() {
        return requestCount == 0 ? 0 : accumulatedRequestTime / requestCount;
    }
    //平均等待时间
    public synchronized long getAverageWaitTime() {
        return hadToWaitCount == 0 ? 0 : accumulatedWaitTime / hadToWaitCount;
    }

    public synchronized long getHadToWaitCount() {
        return hadToWaitCount;
    }

    public synchronized long getBadConnectionCount() {
        return badConnectionCount;
    }

    public synchronized long getClaimedOverdueConnectionCount() {
        return claimedOverdueConnectionCount;
    }
    //平均超时等待时间
    public synchronized long getAverageOverdueCheckoutTime() {
        return claimedOverdueConnectionCount == 0 ? 0 : accumulatedCheckoutTimeOfOverdueConnections / claimedOverdueConnectionCount;
    }
    //平均等待时间
    public synchronized long getAverageCheckoutTime() {
        return requestCount == 0 ? 0 : accumulatedCheckoutTime / requestCount;
    }

    public synchronized int getIdleConnectionCount() {
        return idleConnections.size();
    }

    public synchronized int getActiveConnectionCount() {
        return activeConnections.size();
    }

}
