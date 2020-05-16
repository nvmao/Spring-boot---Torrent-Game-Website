package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.Message;
import com.mao.springboot.gameshop.Entity.Score;
import com.mao.springboot.gameshop.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@EnableScheduling
public class PlayGameRestController {

    @Autowired
    protected SimpMessagingTemplate simpMessagingTemplate;

    private Map<String,Score> map = new HashMap<>();

    private boolean gameStart = false;

    private int time = 60;

    @MessageMapping("/playgame/ready/{to}")
    public void sendReadyMessage(@DestinationVariable String to, Score score){
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

    @Scheduled(fixedRate = 1000)
    public void sendTime(){
        if(gameStart){
            Score score =new Score();
            score.setScore(time--);
            score.setMessage("time");
            for(var user:map.entrySet()){
                simpMessagingTemplate.convertAndSend("/topic/playgame/"+user.getKey(),score);
            }
        }
    }

}
