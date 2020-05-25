package com.mao.springboot.gameshop.RestController.Game;

import com.mao.springboot.gameshop.Entity.OnlineGame.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableScheduling
public class SteroidRestController {

    private final int WIDTH=740,HEIGHT = 480;

    @Autowired
    protected SimpMessagingTemplate simpMessagingTemplate;

    private Map<String, Player> map = new HashMap<>();

    private CoinsList coinsList = new CoinsList();
    private DangerCircleList dangerCircleList = new DangerCircleList();

    private boolean gameStart = false;

    @MessageMapping("/playgame2/ready/{to}")
    public void sendReadyMessage(@DestinationVariable String to, Player player){

        map.put(to,player);
        coinsList.getPlayerScores().put(to,0);

        if(map.size()==2){
            for(var user:map.entrySet()){
                simpMessagingTemplate.convertAndSend("/topic/playgame2/"+user.getKey(),user.getValue());
            }
            gameStart = true;
        }

    }

    @MessageMapping("/playgame2/position/{to}")
    public void sendPlayerPosition(@DestinationVariable String to, Player player){

        if(!gameStart){
            return;
        }

        for(int i = 0 ; i < coinsList.getCoins().size();i++){
            var coin = coinsList.getCoins().get(i);
            if(coin.isCollide(player.getPos(),7)){
                coinsList.getCoins().remove(i);
                coinsList.addScore(player.getUser().getUserName());
            }
        }
        for(int i = 0 ; i < dangerCircleList.getCircles().size();i++){
            var circle = dangerCircleList.getCircles().get(i);
            circle.move();

            if(circle.isCollide(player.getPos(),5)){

                Win win = new Win(to);
                for(var p : map.entrySet()){
                    simpMessagingTemplate.convertAndSend("/topic/playgame2/"+p.getKey(),win);
                }
                gameStart = false;
            }
        }

        simpMessagingTemplate.convertAndSend("/topic/playgame2/"+to,dangerCircleList);
        simpMessagingTemplate.convertAndSend("/topic/playgame2/"+to,coinsList);
        simpMessagingTemplate.convertAndSend("/topic/playgame2/"+to,player);
    }

    @Scheduled(fixedRate = 6000)
    public void randomDanger(){
        if(gameStart && dangerCircleList.getCircles().size() < 10){
            Vector2 position = new Vector2(500,0);
            dangerCircleList.getCircles().add(new DangerCircle(position));
        }
    }

    @Scheduled(fixedRate = 2000)
    public void randomCoin(){
        if(gameStart && coinsList.getCoins().size() < 5){
            Vector2 position = new Vector2((int)(Math.random() * WIDTH),(int)(Math.random()*HEIGHT));
            coinsList.getCoins().add(new Coin(position));
        }
    }

    public void restartGame(){
        gameStart = false;
        coinsList.getCoins().clear();
        dangerCircleList.getCircles().clear();
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
