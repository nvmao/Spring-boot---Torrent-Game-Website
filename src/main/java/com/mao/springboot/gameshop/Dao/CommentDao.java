package com.mao.springboot.gameshop.Dao;

import com.mao.springboot.gameshop.Entity.Comment;
import com.mao.springboot.gameshop.Entity.Game;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CommentDao {
    @Autowired
    private EntityManager entityManager;

    public void add(Comment comment){
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(comment);
    }

    public Comment find(int id){
        Session session = entityManager.unwrap(Session.class);

        Query<Comment> query = session.createQuery("from Comment c where c.id=:cmId ");
        query.setParameter("cmId",id);

        Comment comment = query.getSingleResult();

        return comment;
    }

}
