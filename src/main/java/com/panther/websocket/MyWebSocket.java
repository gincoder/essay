package com.panther.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * scheme
 *
 * @author panther
 * @version 1.0: MyWebSocket.java, 2024/7/11 15:00 $
 */
@ServerEndpoint(value = "/ws")
@Component
@Slf4j
public class MyWebSocket {

    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
    private final View error;

    private Session session;

    public MyWebSocket(View error) {
        this.error = error;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        addOnLineCount();
        log.info("新客官加入，当前人头：{}", getOnlineCount());
        sendMessage("连接建立成功");
    }

    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("IO 异常 , MyWebSocket.sendMessage()");
            throw new RuntimeException(e);
        }
    }

    @OnClose
    public void OnClose() {
        webSocketSet.remove(this);
        offline();
        log.info("有客官下机，当前人头：{}", getOnlineCount());
    }

    @OnMessage
    public void OnMessage(String message) {
        log.info(message);
    }

    @OnError
    public void OnError(Session session, Throwable throwable) {
        log.info("发生错误，MyWebSocket.OnError(p1,p2)");
        throwable.printStackTrace();
    }

    public static synchronized int getOnlineCount() {
        return onlineCount.get();
    }

    public static synchronized void addOnLineCount() {
        onlineCount.incrementAndGet();
    }

    public static synchronized void offline() {
        onlineCount.decrementAndGet();
    }

    public static  CopyOnWriteArraySet getWebsocketSet(){
        return webSocketSet;
    }

}
