package com.panther.algo.BellmanFord;

/**
 * 路由表信息（包含目标地址，出口，距离）
 *
 * @author panther
 * @version 1.0: RouteTableInfo.java, 2024/7/26 11:03 $
 */
public class RouteTableInfo {

    private String aimAdd;

    private String export;

    private Integer distance;


    public RouteTableInfo(String aimAdd, String export, Integer distance) {
        this.aimAdd = aimAdd;
        this.export = export;
        this.distance = distance;
    }

    public String getAimAdd() {
        return aimAdd;
    }

    public void setAimAdd(String aimAdd) {
        this.aimAdd = aimAdd;
    }

    public String getExport() {
        return export;
    }

    public void setExport(String export) {
        this.export = export;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
