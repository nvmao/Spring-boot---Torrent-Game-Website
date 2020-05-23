package com.mao.springboot.gameshop.Entity.RestResponse;

import com.mao.springboot.gameshop.Entity.Game;

import java.util.List;

public class GameList {

    private List<Game> items;
    private int totalCount;

    public GameList(List<Game> items, int totalCount) {
        this.items = items;
        this.totalCount = totalCount;
    }

    public List<Game> getItems() {
        return items;
    }

    public void setItems(List<Game> items) {
        this.items = items;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
