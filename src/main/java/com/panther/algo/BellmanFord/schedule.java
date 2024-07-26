package com.panther.algo.BellmanFord;

import java.util.TimerTask;

/**
 * 定时去更新最新距离 获取所有距离为 1 的邻居节点判断取目标距离的最小值
 *
 * @author panther
 * @version 1.0: schedule.java, 2024/7/26 11:07 $
 */
public class schedule extends TimerTask {
    @Override
    public void run() {
        // 定时去获取更新路由信息
        // 1. 获取注册的所有路由
        RegisterCenter instance = RegisterCenter.getInstance();
        // 2. 获取所有节点更新最近距离
        instance.ROUTE_MAP.forEach((k, v) -> {
            // 3. 获取当前节点的所有邻接节点更新距离
            v.getRouteTable().entrySet()
                    .stream().filter(e -> e.getValue().getDistance() == 1)
                    .forEach(entry -> {
                        // todo 定时更新
                    });
        });
    }
}
