package com.jiuqi.cosmos.inteceper;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by ldd on 2020/1/2.
 * 频道拦截器
 */
@Component
public class SocketChannelIntecepter extends ChannelInterceptorAdapter {


    /**
     * 在消息被实际发送到频道之前调用
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        System.out.println( "消息被实际发送到频道之前调用->preSend" );

        return super.preSend( message, channel );
    }

    /**
     * 发送消息调用后立即调用
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel,
                         boolean sent) {
//        System.out.println( "发送消息调用后立即调用->postSend" );

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap( message );//消息头访问器

        if (headerAccessor.getCommand() == null) return;// 避免非stomp消息类型，例如心跳检测


        Map<String, Object> objectMap = headerAccessor.getSessionAttributes();
        if(objectMap == null || objectMap.size() <= 0) return;

        String sessionId = objectMap.get( "sessionId" ).toString();
        System.out.println( "SocketChannelIntecepter -> sessionId = " + sessionId );

        switch (headerAccessor.getCommand()) {
            case CONNECT:
                System.out.println("连接成功");
                break;
            case DISCONNECT:
                System.out.println("断开连接");
                break;
            case SUBSCRIBE:
                System.out.println("订阅消息");
                break;

            case UNSUBSCRIBE:
                System.out.println("取消订阅");
                break;
            default:
                break;
        }

    }


    /**
     * 在完成发送之后进行调用，不管是否有异常发生，一般用于资源清理
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel,
                                    boolean sent, Exception ex) {
//        System.out.println( "完成发送之后进行调用->afterSendCompletion" );
        super.afterSendCompletion( message, channel, sent, ex );
    }
}
