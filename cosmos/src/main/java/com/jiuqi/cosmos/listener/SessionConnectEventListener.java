package com.jiuqi.cosmos.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * Created by ldd on 2019/12/31.
 */
@Component
public class SessionConnectEventListener implements ApplicationListener<SessionConnectEvent>{

    /**
     * 连接事件监听器
     * 订阅事件监听器+连接事件监听器
     * @param sessionConnectEvent
     */

    @Override
    public void onApplicationEvent(SessionConnectEvent sessionConnectEvent) {
    	System.out.println(StompHeaderAccessor.STOMP_HOST_HEADER);//host
        StompHeaderAccessor wrap = StompHeaderAccessor.wrap( sessionConnectEvent.getMessage() );
        System.out.println(wrap.getHeader("token"));//null  null
        System.out.println(wrap.getDestination());//  topic/SystemInfo null
        System.out.println(wrap.getSessionId());//35bapcrc y2fuk0u4
        System.out.println(wrap.getSubscriptionId());// sub-0
        
        System.out.println(wrap.getMessage());//null
        System.out.println(wrap.getSessionAttributes());//{}
        System.out.println("连接事件监听器"+wrap.getCommand().getMessageType());
         
    }
}
