package com.mao.springboot.gameshop.Config;

import com.mao.springboot.gameshop.Entity.User;
import com.mao.springboot.gameshop.Service.LoginUser;
import com.mao.springboot.gameshop.Service.MyUserDetails;
import com.mao.springboot.gameshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

@Component
public class WebSocketEventListener {

    @Autowired
    private UserService userService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){
        Principal loginUser = (Principal) event.getUser();

        if(loginUser!= null){

            User user = userService.findUserByName(loginUser.getName());
            user.setStatus(true);
            userService.saveOrUpdate(user);
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){

        Principal loginUser = (Principal) event.getUser();

        if(loginUser!= null){

            User user = userService.findUserByName(loginUser.getName());
            user.setStatus(false);
            userService.saveOrUpdate(user);
        }

    }

}
