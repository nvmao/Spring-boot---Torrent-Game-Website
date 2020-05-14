package com.mao.springboot.gameshop.RestController;

import com.mao.springboot.gameshop.Entity.Comment;
import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Entity.Reply;
import com.mao.springboot.gameshop.Entity.User;
import com.mao.springboot.gameshop.Service.CommentService;
import com.mao.springboot.gameshop.Service.LoginUser;
import com.mao.springboot.gameshop.Service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/reply")
public class ReplyRestController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReplyService replyService;

    @PostMapping(value = "/{commentId}",consumes = "application/json", produces = "application/json")
    public Reply addComment(@PathVariable("commentId")int commentId,@RequestBody Reply reply){

        try {
            reply.setUser(LoginUser.getLoginUser());
            reply.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            reply.setComment(commentService.find(commentId));

            replyService.add(reply);
        }catch (Exception e){
            return null;
        }

        return reply;
    }

}
