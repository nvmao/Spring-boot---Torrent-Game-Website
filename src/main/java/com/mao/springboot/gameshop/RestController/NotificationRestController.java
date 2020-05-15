package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.Comment;
import com.mao.springboot.gameshop.Entity.Message;
import com.mao.springboot.gameshop.Entity.User;
import com.mao.springboot.gameshop.Service.ChatMessageService;
import com.mao.springboot.gameshop.Service.CommentService;
import com.mao.springboot.gameshop.Service.LoginUser;
import com.mao.springboot.gameshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NotificationRestController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatMessageService messageService;

    @MessageMapping("/notification/comment-game/{gameId}")
    public void sendMessage(@DestinationVariable("gameId") int gameId, int value){

        User loginUser = userService.findUserById(value);

        List<Comment> comments = commentService.findComments(gameId);

        Map<String,User> usersMap = new HashMap<>();

        for(var comment: comments){
            if(!comment.getUser().getUserName().equals(loginUser.getUserName()) ){
                usersMap.put(comment.getUser().getUserName(),comment.getUser());
            }
        }

        for(var map: usersMap.entrySet()){
            String content = gameId+","+loginUser.getUserName()+" has comment with you ";
            Message message = new Message(content,loginUser,map.getValue(),1);
            messageService.addMessage(message);
            simpMessagingTemplate.convertAndSend("/topic/notification/"+map.getKey(),message);
        }

    }

    @GetMapping("/api/notifications/{to}")
    public List<Message> getNotifications(@RequestParam("page") int page, @PathVariable("to")String to){

        return messageService.getNotification(to,page);
    }

    @GetMapping("/api/notifications/{to}/countUnread")
    public long getNotifications(@PathVariable("to")String to){

        return messageService.countUnreadNotification(to);
    }

    @GetMapping("/api/notifications/{to}/count")
    public long countNotifications(@PathVariable("to")String to){

        return messageService.countNotification(to);
    }

}
