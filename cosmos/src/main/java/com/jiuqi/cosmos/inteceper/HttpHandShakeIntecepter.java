package com.jiuqi.cosmos.inteceper;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by ldd on 2020/1/2.
 *
 *
 * Http握手拦截器,前端连接基站才会触发
 * 前端推送后台消息@MessageMapping 不会触发握手拦截器
 */
@Component
public class HttpHandShakeIntecepter implements HandshakeInterceptor{

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {

        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)serverHttpRequest;
        HttpServletRequest request = servletRequest.getServletRequest();
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        Cookie[] cookies = request.getCookies();
        for(int i=0;i<cookies.length;i++){
            System.out.println("cookies-name:"+cookies[i].getName()+"cookies-value:"+cookies[i].getValue());
        }
        System.out.println("webSocket前置拦截器");
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, @Nullable Exception e) {
        System.out.println("webSocket后置拦截器");
    }
}
