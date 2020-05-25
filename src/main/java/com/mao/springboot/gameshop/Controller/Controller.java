package com.mao.springboot.gameshop.Controller;

import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Entity.Gerne;
import com.mao.springboot.gameshop.Entity.User;
import com.mao.springboot.gameshop.Service.GameService;
import com.mao.springboot.gameshop.Service.GerneService;
import com.mao.springboot.gameshop.Service.LoginUser;
import com.mao.springboot.gameshop.Service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private GameService gameService;

    @Autowired
    private GerneService gerneService;

    @GetMapping()
    public String home(Model model){

        List<Game> loveGames = gameService.findAllSortedByLove(1,1,"all",28);
        List<Game> randomGames = gameService.getRandomGame(5);
        List<Game> latestGames = gameService.findAllSortedByNew(1,1,"all",7);
        List<Game> downloadGames = gameService.findAllSortedByDownload(1,1,"all",7);
        List<Gerne> gernes = gerneService.findAll();


        model.addAttribute("user", LoginUser.getLoginUser());
        model.addAttribute("loveGames",loveGames);
        model.addAttribute("randomGames",randomGames);
        model.addAttribute("latestGames",latestGames);
        model.addAttribute("downloadGames",downloadGames);
        model.addAttribute("gernes",gernes);

        return "home";
    }


    @GetMapping("/login")
    public String loginPage(){
        return "/user/login";
    }


    @GetMapping("/chat")
    public String chat(){
        return "chat";
    }

    @GetMapping(value = "/playgame")
    public String playGameColor(@RequestParam("from")String from, @RequestParam("to")String to){

        return "playgame/play-game";
    }

    @GetMapping(value = "/playgame2")
    public String playGameSteroid(@RequestParam("from")String from, @RequestParam("to")String to){

        return "playgame/play-game-steroid";
    }

}
