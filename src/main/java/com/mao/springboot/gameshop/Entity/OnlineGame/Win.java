package com.mao.springboot.gameshop.Entity.OnlineGame;

public class Win {
    private String message = "win";
    private String whoWin;

    public Win(String whoWin) {
        this.whoWin = whoWin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getWhoWin() {
        return whoWin;
    }

    public void setWhoWin(String whoWin) {
        this.whoWin = whoWin;
    }
}
