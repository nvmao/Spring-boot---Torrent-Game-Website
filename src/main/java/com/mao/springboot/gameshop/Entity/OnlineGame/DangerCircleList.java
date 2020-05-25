package com.mao.springboot.gameshop.Entity.OnlineGame;

import java.util.ArrayList;
import java.util.List;

public class DangerCircleList {

    private List<DangerCircle> circles = new ArrayList<>();
    private String message = "danger";


    public List<DangerCircle> getCircles() {
        return circles;
    }

    public void setCircles(List<DangerCircle> circles) {
        this.circles = circles;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
