package com.jiuqi.cosmos.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.jiuqi.cosmos.pojo.InMessage;
import com.jiuqi.cosmos.service.impl.WebSocketServiceImpl;

/**
 * Created by ldd on 2020/1/2.
 */
@Controller
public class WebSocketController {


    @Autowired
    private WebSocketServiceImpl webSocketService;

    @MessageMapping("/chat/message")//用于接收前端的数据，前端发送的数据类型是inMessage
    public void onChatMessage(@RequestBody InMessage inMessage){
    	//fromName,toName,content 服务的根据客户端发来的消息，进行后台相关处理操作，如关注  建立了A与B之间的连接，当B发送博客成功时，遍历B的关注者，发送消息
        webSocketService.onChatUserMessage( inMessage );
    }
    @MessageMapping("/system/message")//用于接收前端的数据，前端发送的数据类型是inMessage
    @SendTo("/topic/SystemInfo")
    public void onServerMessage(@RequestBody InMessage inMessage){//fromName,toName,content
    	System.out.println("收到信息："+inMessage.toString());
        webSocketService.onPushSystemInfo( inMessage );
    }
    @Scheduled(fixedRate = 2000)
    public void onPushWebSystemInfo(){//服务器主动向前端发送消息
        webSocketService.onPushSystemInfo();
    }
}
