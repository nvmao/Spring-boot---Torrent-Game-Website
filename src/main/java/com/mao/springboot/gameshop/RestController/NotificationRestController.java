package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationRestController {

    @Autowired
    protected SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/notification/{to}")
    public void sendMessage(@DestinationVariable String to, Message message){

        message.setType(1); // comment

        simpMessagingTemplate.convertAndSend("/topic/notification/"+to,message);
    }

}
