package com.panther.designModule.factory;

import java.util.Map;

public interface ICommodity {

    /**
     * 发放奖励抽象接口
     * @param uId 用户id
     * @param commodityId 奖品id
     * @param bizId 业务id
     * @param extMap 扩展字段
     */
    void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception;

}