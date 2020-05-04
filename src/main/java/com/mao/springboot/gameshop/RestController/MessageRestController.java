package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.MessageModel;
import com.mao.springboot.gameshop.Entity.User;
import com.mao.springboot.gameshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRestController {

    @Autowired
    protected SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to,MessageModel message){
        System.out.println("handle ling send message: " + message + " to " + to);

        simpMessagingTemplate.convertAndSend("/topic/messages/"+to,message);

    }

}
