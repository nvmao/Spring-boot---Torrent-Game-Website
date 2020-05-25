package com.mao.springboot.gameshop.Entity.OnlineGame;

public class DangerCircle {

    private Vector2 pos;
    private int radius = 35;

    private Vector2 vel = new Vector2(1,1);
    public DangerCircle(){}


    public void move(){
        pos.setX(pos.getX()+vel.getX());
        pos.setY(pos.getY()+vel.getY());

        if(pos.getX() < 0 || pos.getX() > 740){
            vel.setX(vel.getX()*-1);
        }
        if(pos.getY() < 0 || pos.getY() > 480){
            vel.setY(vel.getY()*-1);
        }
    }

    public boolean isCollide(Vector2 playerPos,int playerRadius){

        var dx = pos.getX() - playerPos.getX();
        var dy =  pos.getY() - playerPos.getY();
        var distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < radius + playerRadius) {
            return true;
        }

        return false;
    }

    public DangerCircle(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
