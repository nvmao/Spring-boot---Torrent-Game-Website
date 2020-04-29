package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Dao.CommentDao;
import com.mao.springboot.gameshop.Dao.ReplyDao;
import com.mao.springboot.gameshop.Entity.Comment;
import com.mao.springboot.gameshop.Entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReplyService {
    @Autowired
    private ReplyDao replyDao;

    @Transactional
    public void add(Reply reply){
        replyDao.add(reply);
    }


}
