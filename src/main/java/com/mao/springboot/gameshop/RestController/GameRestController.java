package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameRestController {

    @Autowired
    private GameService gameService;

    @GetMapping()
    public List<Game> gameList(){
        List<Game> games = null;
            games = gameService.findAll();
        return games;
    }

    @GetMapping(params = "page")
    public List<Game> gameList(@Param("page")int page){
        List<Game> games = null;
        if(page > 0){
            games = gameService.findAll(page);
        }
        else {
            games = gameService.findAll();
        }


        return games;
    }

    @GetMapping(params = {"page","sort","order"})
    public List<Game> gameList(@Param("page") int page, @Param("sort") String sort,@Param("order")int order){
        List<Game> games = null;

        if(order==0){
            order = 1;
        }
        if(page==0){
            page=1;
        }

        //System.out.println("page: "+page+", sort: "+sort+", order: "+order);

        if(page > 0){
            if(sort.equals("new")){
                games = gameService.findAllSortedByNew(page,order);
            }
            if(sort.equals("love")){
                games = gameService.findAllSortedByLove(page,order);
            }
            if(sort.equals("download")){
                games = gameService.findAllSortedByDownload(page,order);
            }
        }

        return games;
    }


    @GetMapping("/{id}")
    public Game getGame(@PathVariable("id")int id){

        Game game = gameService.find(id);

        return game;
    }

    @GetMapping("/download/{id}")
    public int donwloadGame(@PathVariable int id){
        Game game = gameService.find(id);

        game.setDownloadCount(game.getDownloadCount()+1);

        gameService.save(game);

        return game.getDownloadCount();
    }

}
