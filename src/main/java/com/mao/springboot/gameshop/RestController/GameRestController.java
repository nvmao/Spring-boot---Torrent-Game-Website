package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameRestController {

    @Autowired
    GameService gameService;

    @GetMapping()
    public List<Game> gameList(@RequestParam("page")int page){
        List<Game> games = null;
        if(page > 0){
            games = gameService.findAll(page);
        }
        else {
            games = gameService.findAll();
        }


        return games;
    }

    @GetMapping("/{id}")
    public Game getGame(@PathVariable("id")int id){

        Game game = gameService.find(id);

        return game;
    }

}
