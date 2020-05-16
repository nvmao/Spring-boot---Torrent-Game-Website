package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.Message;
import com.mao.springboot.gameshop.Service.ChatMessageService;
import com.mao.springboot.gameshop.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class MessageRestController {

    @Autowired
    private FileService fileService;

    @Autowired
    protected SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping("/chat/{to}/playgame")
    public void playgame(@DestinationVariable("to") String to, Message message){

        simpMessagingTemplate.convertAndSend("/topic/messages/"+to,message);

    }

    @MessageMapping("/chat/{to}/typing")
    public void typing(@DestinationVariable("to") String to, Message message){

        simpMessagingTemplate.convertAndSend("/topic/messages/"+to,message);

    }

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, Message message){

        message.setType(0); //0 is chat message
        if(message.getToUser().getStatus() == false){
            message.setRead(false);
        }
        chatMessageService.addMessage(message);

        simpMessagingTemplate.convertAndSend("/topic/messages/"+to,message);
    }

    @GetMapping("/chat-messages/{from}/{to}")
    public List<Message> getMessages(@PathVariable("from") String from, @PathVariable("to") String to,
                                     @RequestParam("page")int page){

        chatMessageService.readAllMessages(from,to);
        List<Message> messages = chatMessageService.getChatMessages(from,to,page);


        return messages;
    }

    @GetMapping("/chat-messages/countUnread/{from}/{to}")
    public long countUnreadMessage(@PathVariable("from") String from,@PathVariable("to") String to){

        long count = chatMessageService.countUnreadMessage(from,to);

        return count;
    }

    @PostMapping(value = "/chat-messages/upload/{from}/{to}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestParam("photo")MultipartFile file,
                                 @PathVariable("from")String from, @PathVariable("to")String to){

        String strPath = "/uploads/message/"+from+"+"+to+"+"+file.getOriginalFilename();
        try{
            fileService.saveMessageImage(file,strPath);
        }catch (Exception e){
            System.out.println(e);
            return "failed";
        }
        return strPath;
    }

    @GetMapping(value = "/chat-messages/max-page/{from}/{to}")
    public int CountMaxPageOfMessages(@PathVariable("from")String from, @PathVariable("to")String to){

        long count = chatMessageService.countMessage(from,to);

        int maxPage = (int)(count/20) + 1;

        return maxPage;
    }

}
