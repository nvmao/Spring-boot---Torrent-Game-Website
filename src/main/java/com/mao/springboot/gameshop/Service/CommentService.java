package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Dao.CommentDao;
import com.mao.springboot.gameshop.Entity.Comment;
import com.mao.springboot.gameshop.Entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Transactional
    public void add(Comment comment){
        commentDao.add(comment);
    }

    @Transactional
    public Comment find(int id){
        return commentDao.find(id);
    }

    public List<Comment> findComments(int gameID){
        return commentDao.findComments(gameID);
    }
}
