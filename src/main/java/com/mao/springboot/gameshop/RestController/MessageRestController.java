package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.ChatMessage;
import com.mao.springboot.gameshop.Service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageRestController {

    @Autowired
    protected SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, ChatMessage message){
        System.out.println("handle ling send message: " + message + " to " + to);

        if(message.getToUser().getStatus() == false){
            message.setRead(false);
        }
        chatMessageService.addMessage(message);

        simpMessagingTemplate.convertAndSend("/topic/messages/"+to,message);
    }

    @GetMapping("/chat-messages/{from}/{to}")
    public List<ChatMessage> getMessages(@PathVariable("from") String from,@PathVariable("to") String to){

        chatMessageService.readAllMessages(from,to);
        List<ChatMessage> messages = chatMessageService.getChatMessages(from,to);


        return messages;
    }

    @GetMapping("/chat-messages/countUnread/{from}/{to}")
    public long countUnreadMessage(@PathVariable("from") String from,@PathVariable("to") String to){

        long count = chatMessageService.countUnreadMessage(from,to);

        return count;
    }

}
