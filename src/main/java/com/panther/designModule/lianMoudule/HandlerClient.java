package com.panther.designModule.lianMoudule;

public class HandlerClient {
    public static void main(String[] args) {

        FirstPassHandler firstPassHandler = new FirstPassHandler();// 第一关
        SecondPassHandler secondPassHandler = new SecondPassHandler();// 第二关
        ThirdPassHandler thirdPassHandler = new ThirdPassHandler();// 第三关

        firstPassHandler.setSecondPassHandler(secondPassHandler);// 第一关的下一关是第二关
        secondPassHandler.setThirdPassHandler(thirdPassHandler);// 第二关的下一关是第三关

        // 说明：因为第三关是最后一关，因此没有下一关
        // 开始调用第一关 每一个关卡是否进入下一关卡 在每个关卡中判断
        firstPassHandler.handler();

    }
}