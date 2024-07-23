package com.panther.designModule.lianMoudule;

public class SecondPassHandler {

    /**
     * 第二关的下一关是 第三关
     */
    private ThirdPassHandler thirdPassHandler;

    public void setThirdPassHandler(ThirdPassHandler thirdPassHandler) {
        this.thirdPassHandler = thirdPassHandler;
    }

    // 本关卡游戏得分
    private int play(){
        return 90;
    }

    public int handler(){
        System.out.println("第二关-->SecondPassHandler 玩家得分： " + play());

        if(play() >= 90){
            // 分数>=90 并且存在下一关才进入下一关
            if(this.thirdPassHandler != null){
                return this.thirdPassHandler.handler();
            }
        }

        return 90;
    }
}