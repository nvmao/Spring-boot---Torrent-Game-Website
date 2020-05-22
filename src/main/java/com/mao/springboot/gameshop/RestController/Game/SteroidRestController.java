package com.mao.springboot.gameshop.RestController.Game;

import com.mao.springboot.gameshop.Entity.OnlineGame.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@EnableScheduling
public class SteroidRestController {

    @Autowired
    protected SimpMessagingTemplate simpMessagingTemplate;

    private Map<String, Player> map = new HashMap<>();

    private boolean gameStart = false;

    @MessageMapping("/playgame2/ready/{to}")
    public void sendReadyMessage(@DestinationVariable String to, Player player){

        map.put(to,player);
        if(map.size()==2){
            for(var user:map.entrySet()){
                simpMessagingTemplate.convertAndSend("/topic/playgame2/"+user.getKey(),user.getValue());
            }
            gameStart = true;
        }

    }

    @MessageMapping("/playgame2/position/{to}")
    public void sendPlayerPosition(@DestinationVariable String to, Player player){
        simpMessagingTemplate.convertAndSend("/topic/playgame2/"+to,player);
    }


    public void restartGame(){
        gameStart = false;
        if(map.size() >1){
            map.clear();
        }

    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){

        Principal loginUser = (Principal) event.getUser();

        if(loginUser!= null){

            restartGame();
        }

    }

}
