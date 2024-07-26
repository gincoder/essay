package com.panther.algo.BellmanFord;

import java.util.HashMap;
import java.util.Map;

/**
 * 路由注册中心
 *
 * @author panther
 * @version 1.0: RegistCenter.java, 2024/7/26 11:08 $
 */
public class RegisterCenter {

    private static volatile RegisterCenter singleton;

    final static Map<String, RouteNode> ROUTE_MAP = new HashMap<>();

    private RegisterCenter() {

    }

    /**
     * @param ip       自身地址
     * @param neighbor 邻居地址
     * @param export   出口
     * @param aimAdd   目标地址
     */
    public static void registerNode(String ip, String neighbor, String export, String aimAdd) {
        RouteNode node = ROUTE_MAP.get(neighbor);
        if (node == null) {
            throw new RuntimeException(neighbor + " neighbor node not found ,first append node neighbor!");
        }
        RouteTableInfo routeTableInfo = new RouteTableInfo(aimAdd, export, node.getRouteTable().get(neighbor).getDistance());
        RouteNode routeNode = new RouteNode(ip);
        routeNode.getRouteTable().put(ip, routeTableInfo);
        // todo 同步注册中心的路由信息  不同点在于距离
        ROUTE_MAP.forEach((k, v) -> {
            Map<String, RouteTableInfo> routeTable = v.getRouteTable();
            RouteTableInfo mapInfo = routeTable.get(k);
            RouteTableInfo info = new RouteTableInfo(mapInfo.getAimAdd()
                    , mapInfo.getExport(), mapInfo.getDistance() + 1);
            routeNode.getRouteTable().put(k, info);
        });
        ROUTE_MAP.put(ip, routeNode);
    }

    public static RegisterCenter getInstance() {
        if (singleton == null) {
            synchronized (RegisterCenter.class) {
                if (singleton == null) singleton = new RegisterCenter();
            }
        }
        return singleton;
    }

}
