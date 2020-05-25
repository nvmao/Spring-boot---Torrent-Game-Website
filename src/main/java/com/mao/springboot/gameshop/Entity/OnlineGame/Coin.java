package com.mao.springboot.gameshop.Entity.OnlineGame;

public class Coin {

    private Vector2 position;
    private int radius = 20;


    public boolean isCollide(Vector2 playerPos,int playerRadius){

        var dx = position.getX() - playerPos.getX();
        var dy =  position.getY() - playerPos.getY();
        var distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < radius + playerRadius) {
           return true;
        }

        return false;
    }


    public Coin(Vector2 position) {
        this.position = position;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
