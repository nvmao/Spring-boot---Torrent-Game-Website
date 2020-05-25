package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Entity.Gerne;
import com.mao.springboot.gameshop.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreRestController {

    @Autowired
    private GameService gameService;

    @GetMapping("api/genres/{gameId}")
    public List<Gerne> Getenres(@PathVariable("gameId")int gameId){
        Game game = gameService.find(gameId);

        return game.getGernes();
    }

}
