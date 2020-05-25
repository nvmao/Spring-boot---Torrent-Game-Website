package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Entity.RestResponse.GameList;
import com.mao.springboot.gameshop.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping(params = "q")
    public GameList searchGame(@Param("q")String q, HttpServletRequest request){

        List<Game> games = gameService.search(q);
        for(var game:games){
            game.setUrl(request.getContextPath()+"/games/"+game.getId());
        }


        return new GameList(games,games.size());
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

    @GetMapping(params = {"page","sort","order","genre"})
    public List<Game> gameList(@Param("page") int page, @Param("sort") String sort,@Param("order")int order,@Param("genre")String genre){
        List<Game> games = null;

        if(order==0){
            order = 1;
        }
        if(page==0){
            page=1;
        }


        if(page > 0){
            if(sort.equals("new")){
                games = gameService.findAllSortedByNew(page,order,genre,28);
            }
            if(sort.equals("love")){
                games = gameService.findAllSortedByLove(page,order,genre,28);
            }
            if(sort.equals("download")){
                games = gameService.findAllSortedByDownload(page,order,genre,28);
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
