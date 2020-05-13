package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Entity.Love;
import com.mao.springboot.gameshop.Entity.LoveResponse;
import com.mao.springboot.gameshop.Entity.User;
import com.mao.springboot.gameshop.Service.GameService;
import com.mao.springboot.gameshop.Service.LoginUser;
import com.mao.springboot.gameshop.Service.LoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoveRestController {

    @Autowired
    private LoveService loveService;

    @Autowired
    private GameService gameService;

    @GetMapping("/api/games/{id}/love")
    public long love(@PathVariable("id") int gameId){

        User user = LoginUser.getLoginUser();

        if(user == null){
            return  loveService.countLove(gameId);
        }

        Game game = gameService.find(gameId);

        boolean isLoved = loveService.isLoved(user.getId(),gameId);

        if(!isLoved){
            Love love = new Love();
            love.setGame(game);
            love.setUser(user);
            loveService.add(love);
            return  loveService.countLove(gameId);
        }

        loveService.remove(user.getId(),gameId);

        return  loveService.countLove(gameId);
    }

    @GetMapping("/api/games/{id}/is-love")
    public boolean isLove(@PathVariable("id") int gameId){
        User user = LoginUser.getLoginUser();
        if(user == null){
            return false;
        }
        boolean isLoved = loveService.isLoved(user.getId(),gameId);
        return isLoved;
    }



}
