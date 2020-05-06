package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.User;
import com.mao.springboot.gameshop.Service.LoginUser;
import com.mao.springboot.gameshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/socket")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/fetchAllUsers")
    public List<User> fetchAll(){
        return userService.findAll();
    }

    @GetMapping("/loginUser")
    public User getLoginUser(){
        return LoginUser.getLoginUser();
    }

    @GetMapping("/users/{userName}")
    public User getUser(@PathVariable("userName") String userName){
        return userService.findUserByName(userName);
    }

}
