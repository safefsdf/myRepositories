package com.jiuqi.cosmos.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * Created by ldd on 2019/12/31.
 */
@Component
public class SessionSubscribeEventListener implements ApplicationListener<SessionSubscribeEvent>{

    /**
     * 订阅事件监听器
     * @param sessionSubscribeEvent
     */

    @Override
    public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
        StompHeaderAccessor wrap = StompHeaderAccessor.wrap( sessionSubscribeEvent.getMessage() );
         
        System.out.println("订阅事件监听器"+wrap.getCommand().getMessageType());
        
    }
}
