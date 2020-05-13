package com.mao.springboot.gameshop.Dao;

import com.mao.springboot.gameshop.Entity.Love;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LoveDao {

    @Autowired
    private EntityManager entityManager;

    public long countLove(int gameId){

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("" +
                "select count(*) from Love where game.id=:id");
        query.setParameter("id",gameId);

        return (long)query.getSingleResult();
    }

    public boolean isLoved(int userId,int gameId){
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("" +
                "select count(*) from Love where game.id=:gameId and user.id=:userId");
        query.setParameter("gameId",gameId);
        query.setParameter("userId",userId);

        long result = (long)query.getSingleResult();

        return result==1;
    }

    public void add(Love love){
        Session session = entityManager.unwrap(Session.class);
        session.save(love);
    }

    public void remove(int userId,int gameId){
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("" +
                "delete Love where game.id=:gameId and user.id=:userId");
        query.setParameter("gameId",gameId);
        query.setParameter("userId",userId);
        query.executeUpdate();
    }

}
