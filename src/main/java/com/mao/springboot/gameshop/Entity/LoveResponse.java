package com.mao.springboot.gameshop.Entity;

public class LoveResponse {

    private long count;
    private int type;

    public LoveResponse(long count, int type) {
        this.count = count;
        this.type = type;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
