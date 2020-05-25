package com.mao.springboot.gameshop.Entity.OnlineGame;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class CoinsList {
    private List<Coin> coins = new ArrayList<>();
    private String message = "coin";
    private Map<String ,Integer> playerScores = new Hashtable<>();

    public Map<String, Integer> getPlayerScores() {
        return playerScores;
    }

    public void addScore(String to){
        playerScores.replace(to,playerScores.get(to)+1);
    }

    public void setPlayerScores(Map<String, Integer> playerScores) {
        this.playerScores = playerScores;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
