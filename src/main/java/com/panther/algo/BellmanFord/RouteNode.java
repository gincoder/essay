package com.panther.algo.BellmanFord;

import java.util.HashMap;
import java.util.Map;

/**
 * 路由节点包含路由表和自己的路由信息
 *
 * @author panther
 * @version 1.0: RouteNode.java, 2024/7/26 11:02 $
 */
public class RouteNode {

    // key: ip  value: 所有出口节点信息
    Map<String, RouteTableInfo> routeTable = new HashMap<>();

    private String routeInfo;

    public RouteNode(String routeInfo) {
        this.routeInfo = routeInfo;
    }

    public Map<String, RouteTableInfo> getRouteTable() {
        return routeTable;
    }

    public void setRouteTable(Map<String, RouteTableInfo> routeTable) {
        this.routeTable = routeTable;
    }

    public String getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(String routeInfo) {
        this.routeInfo = routeInfo;
    }
}
