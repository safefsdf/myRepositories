package com.jiuqi.cosmos.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

/**
 * Created by ldd on 2019/12/31.
 */
@Component
public class SessionUnsubscribeEventListener implements ApplicationListener<SessionUnsubscribeEvent>{

    /**
     * 取消订阅事件监听器
     * @param sessionUnsubscribeEvent
     */

    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent sessionUnsubscribeEvent) {
        StompHeaderAccessor wrap = StompHeaderAccessor.wrap( sessionUnsubscribeEvent.getMessage() );
        System.out.println("取消订阅事件监听器"+wrap.getCommand().getMessageType());
    }
}
