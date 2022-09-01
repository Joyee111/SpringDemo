package com.example.serious.demo.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@ServerEndpoint(value = "/websocket/{username}")
@Slf4j
public class WebSocket {

    public static int onlineCount = 0;
    public static Map<String, WebSocket> clients = new HashMap<String, WebSocket>();
    public Session session;
    public String username;

    public String getUsername() {
        return username;
    }

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        this.username = username;
        this.session = session;
        addOnlineCount();
        clients.put(session.getId(), this);
        log.warn("已连接:" + username);
    }

    @OnClose
    public void onClose() throws IOException {
        log.warn("断开" + username);
        clients.remove(session.getId());
        subOnlineCount();
    }

    //收到客户端消息后调用的方法
    @OnMessage
    public void onMessage(String message) throws IOException {
        if (message.equals("ping")) {
            this.session.getAsyncRemote().sendText("pang");
        } else if ("close".equals(message)) {
            onClose();
            this.session.close();
        } else {
            log.warn(this.username + "收到 " + message);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public static String sendMessageTo(String json) {
        Map<String, String> parse = (Map<String, String>) JSONObject.parse(json);
        String userCode = parse.get("usercode");
        for (WebSocket item : clients.values()) {
            if (userCode.equals(item.getUsername())) {
                item.session.getAsyncRemote().sendText(json);
            }

        }
        return "DONE";
    }

    public static void clearMessageFromOpen(String message) {
        try {
            JSONObject json = JSONObject.parseObject(message);
            if (json.get("uuid") != null) {
            }
        } catch (Exception e) {
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

    public static synchronized Map<String, WebSocket> getClients() {
        return clients;
    }

}