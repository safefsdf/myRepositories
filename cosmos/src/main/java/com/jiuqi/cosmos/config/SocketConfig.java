package com.jiuqi.cosmos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.jiuqi.cosmos.inteceper.HttpHandShakeIntecepter;
import com.jiuqi.cosmos.inteceper.SocketChannelIntecepter;

@Configuration
@EnableWebSocketMessageBroker
public class SocketConfig implements WebSocketMessageBrokerConfigurer {
	// 注册端点(基站) 任何发布订阅消息时吗，需要连接此端点
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		/**
		 * /endpoint-websocket:基站名称 setAllowedOrigins( "*" ):非必须 *表示允许其他域进行连接
		 * withSockJS：表示支持sockJs连接
		 */
		registry.addEndpoint("/ws-vue").addInterceptors(new HttpHandShakeIntecepter()).setAllowedOrigins("*")
				.withSockJS();
	}

	/**
	 * 配置消息代理（中介） enableSimpleBroker:服务端推送给客户端的路径前缀
	 * setApplicationDestinationPrefixes:客户端发送给服务端的路径前缀
	 *
	 * @param registry
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic", "/chat");
		registry.setApplicationDestinationPrefixes("/app");
	}

	// 配置客户端进入通道
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new SocketChannelIntecepter());
	}

	// 配置客户端退出通道
	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		registration.interceptors(new SocketChannelIntecepter());
	}

}
