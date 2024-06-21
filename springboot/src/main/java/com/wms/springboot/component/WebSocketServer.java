/*
 *功能：
 *日期：2024/6/18 16:01
 *作者：
 */
package com.wms.springboot.component;


import com.wms.springboot.common.ApplicationContextHolder;
import com.wms.springboot.entity.Editor;
import com.wms.springboot.service.EditorService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/server/{userId}/{eId}")
@Component
public class WebSocketServer implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    private static Map<String,Session>sessionMap = new ConcurrentHashMap<>();
    public static final Map<String,Object>userMap = new ConcurrentHashMap<>();

    @Resource
    EditorService editorService;
    static EditorService staticEditorService;

    @OnOpen
    public void onopen(Session session, @PathParam("userId")Integer userId,@PathParam("eId")Integer eId){
        sessionMap.put(session.getId(),session);
        log.info("[onOpen] 新建连接，session={},当前在线人数:{}",session.getId(),sessionMap.size());
        //System.out.println("连接建立");

        // 使用ApplicationContextHolder获取Spring Bean
         editorService = ApplicationContextHolder.getBean(EditorService.class);
       //  this.sendAllMessage();
    }

    @OnClose
    public void onclose(Session session){
        sessionMap.remove(session.getId());
        log.info("[onClose] 新建连接，session={},当前在线人数:{}",session.getId(),sessionMap.size());
        System.out.println("连接关闭");
    }

    @OnMessage//服务端收到消息后触发，向客户端进行的动作
    public void onMessage(String message,Session session,@PathParam("eId")Integer eId){

        try {
           //System.out.println(message);
           // System.out.println(eId);
           // session.getBasicRemote().sendText("收到消息");

            //消息更新到数据库
            //editorService.updateEditorContent(eId,message);

            // 发送消息给客户端

            //session.getBasicRemote().sendText(message);
            this.sendAllMessage(message);

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @OnError
    public void onerror(Session session,Throwable error){log.error("[onError] 发生错误",error);}

    /*
    * 服务端发送消息给除了自己的其他客户端
    * */
    private void sendMessage(Session fromSession,String message){
        sessionMap.values().forEach(session -> {
            if (fromSession!=session){
                log.info("服务端给客户端[{}]发送消息{}",session.getId(),message);
                try {
                    session.getBasicRemote().sendText(message);
                }catch (IOException e){
                    log.error("服务端发送消息给客户端异常",e);
                }
            }
        });
    }

    private void sendAllMessage(String message){
        try {
            for (Session session : sessionMap.values()) {
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        }catch(Exception e){
                log.error("服务端发送消息给客户端失败",e);
            }
        }


    @Override
    public void afterPropertiesSet(){staticEditorService = editorService;}
}
