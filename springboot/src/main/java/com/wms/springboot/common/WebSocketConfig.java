/*
 *功能：
 *日期：2024/6/18 15:55
 *作者：
 */
package com.wms.springboot.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    /*
     *
     * 注入一个ServerEndpointExporter,改Bean会自动注册使用@ServerEndpoint注解中声明的websocket endpoint
     * */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}