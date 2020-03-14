package com.jiuqi.cosmos.service.impl;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.jiuqi.cosmos.pojo.InMessage;
import com.jiuqi.cosmos.pojo.OutMessage;

/**
 * Created by ldd on 2019/12/31.
 */
@Service
public class WebSocketServiceImpl {

    //注入消息模板
    @Autowired
	public SimpMessagingTemplate messagingTemplate;


    //服务器主动 
    public void onPushSystemInfo(InMessage inMessage){
        messagingTemplate.convertAndSend( "/topic/SystemInfo", " 用户 "+inMessage.getFromName()+" 向用户 "+inMessage.getToName()+" 发送了"+inMessage.getContent() );
    }
    //服务器主动，推送SystemInfo
    public void onPushSystemInfo(){
        messagingTemplate.convertAndSend( "/topic/SystemInfo", "系统消息");
    }



    //单聊
    public void onChatUserMessage(InMessage inMessage){
        OutMessage outMessage = new OutMessage(  );
        outMessage.setContent( inMessage.getContent() );
        outMessage.setFromName( inMessage.getFromName() );
        
        messagingTemplate.convertAndSend( "/chat/"+inMessage.getToName(), outMessage);
    }

}
