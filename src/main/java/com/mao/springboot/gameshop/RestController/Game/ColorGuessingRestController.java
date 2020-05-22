package com.mao.springboot.gameshop.RestController.Game;

import com.mao.springboot.gameshop.Entity.OnlineGame.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@EnableScheduling
public class ColorGuessingRestController {

    @Autowired
    protected SimpMessagingTemplate simpMessagingTemplate;

    private Map<String, Score> map = new HashMap<>();

    private boolean gameStart = false;

    private int time = 60;

    @MessageMapping("/playgame/ready/{to}")
    public void sendReadyMessage(@DestinationVariable String to, Score score){

        time=60;
        gameStart = false;

        map.put(to,score);
        if(map.size()==2){
            for(var user:map.entrySet()){
                simpMessagingTemplate.convertAndSend("/topic/playgame/"+user.getKey(),user.getValue());
            }
            gameStart = true;
        }

    }

    @MessageMapping("/playgame/score/{to}")
    public void sendScoreMessage(@DestinationVariable String to, Score score){

        simpMessagingTemplate.convertAndSend("/topic/playgame/"+to,score);
    }

    public void restartGame(){
        time=60;
        gameStart = false;
        if(map.size() >1){
            map = new HashMap<>();
        }

    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){

        Principal loginUser = (Principal) event.getUser();

        if(loginUser!= null){

           restartGame();
        }

    }

    @Scheduled(fixedRate = 1000)
    public void sendTime(){
        if(gameStart){
            Score score =new Score();
            score.setScore(time--);
            score.setMessage("time");
            for(var user:map.entrySet()){
                simpMessagingTemplate.convertAndSend("/topic/playgame/"+user.getKey(),score);
            }
            if(time < 0){
                restartGame();
            }
        }
    }

}
