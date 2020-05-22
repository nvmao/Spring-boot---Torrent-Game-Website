package com.mao.springboot.gameshop.Entity.OnlineGame;

import com.mao.springboot.gameshop.Entity.User;

public class Score {
    private User player;
    private int score;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
