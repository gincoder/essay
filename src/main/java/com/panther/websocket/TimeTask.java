package com.panther.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: TimeTask.java, 2024/7/11 15:22 $
 */
@Component
@Slf4j
@EnableScheduling
public class TimeTask {

    @Scheduled(cron = "0/20 * * * * ?")
    public void test(){
        CopyOnWriteArraySet<MyWebSocket> websocketSet =
                MyWebSocket.getWebsocketSet();

        websocketSet.forEach(e -> {
            e.sendMessage("当前时间是：" + LocalDateTime.now());
        });

    }

}
