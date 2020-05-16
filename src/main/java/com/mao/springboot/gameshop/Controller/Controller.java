package com.mao.springboot.gameshop.Controller;

import com.mao.springboot.gameshop.Entity.User;
import com.mao.springboot.gameshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping()
    public String home(){
        return "redirect:/games";
    }

    @GetMapping("/chat")
    public String chat(){
        return "chat";
    }

    @GetMapping(value = "/playgame")
    public String playGame(@RequestParam("from")String from, @RequestParam("to")String to){

        return "play-game";
    }

}
