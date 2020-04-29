package com.mao.springboot.gameshop.Dao;

import com.mao.springboot.gameshop.Entity.Comment;
import com.mao.springboot.gameshop.Entity.Reply;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ReplyDao {
    @Autowired
    private EntityManager entityManager;

    public void add(Reply reply){
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(reply);
    }
}
