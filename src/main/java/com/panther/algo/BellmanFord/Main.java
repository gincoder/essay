package com.panther.algo.BellmanFord;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: Main.java, 2024/7/26 11:06 $
 */
public class Main {

    /**
     * 构建节点信息
     *     3 —— 6
     *   /  \    \
     *  1 —— 2 —— 5
     *   \  /
     *    4
     */
    public static void main(String[] args) {
        RouteTableInfo routeTableInfo1 = new RouteTableInfo("192.168.1.2", "192.168.1.1/20", 1);
        RouteTableInfo routeTableInfo2 = new RouteTableInfo("192.168.1.3", "192.168.1.1/20", 1);

    }

}
