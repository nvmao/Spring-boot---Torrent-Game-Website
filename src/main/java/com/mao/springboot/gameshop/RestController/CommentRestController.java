package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.Comment;
import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Entity.User;
import com.mao.springboot.gameshop.Service.CommentService;
import com.mao.springboot.gameshop.Service.GameService;
import com.mao.springboot.gameshop.Service.LoginUser;
import com.mao.springboot.gameshop.Service.UserService;
import org.hibernate.resource.jdbc.LogicalConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;

    @GetMapping("/{gameId}")
    public List<Comment> getComments(@PathVariable("gameId")int gameId){
        return commentService.findComments(gameId);
    }

    @PostMapping(value = "/{gameId}",consumes = "application/json", produces = "application/json")
    public Comment addComment(@RequestBody Comment comment){

        try {
            Game game = gameService.find(comment.getGameId());
            User user = LoginUser.getLoginUser();
            comment.setUser(user);
            comment.setGame(game);
            comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            commentService.add(comment);
        }catch (Exception e){
            System.out.println("comment: "+e);
            return null;
        }

        return comment;
    }

}
