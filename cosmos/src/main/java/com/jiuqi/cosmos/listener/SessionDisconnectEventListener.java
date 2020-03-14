package com.jiuqi.cosmos.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Created by ldd on 2019/12/31.
 */
@Component
public class SessionDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent>{

    /**
     * 断开连接事件监听器
     * @param sessionDisconnectEvent
     */

    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        StompHeaderAccessor wrap = StompHeaderAccessor.wrap( sessionDisconnectEvent.getMessage() );
        System.out.println("断开连接事件监听器"+wrap.getCommand().getMessageType());
    }
}
