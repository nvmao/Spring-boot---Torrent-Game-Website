package com.mao.springboot.gameshop.Dao;

import com.mao.springboot.gameshop.Entity.Publisher;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PublisherDao {
    @Autowired
    EntityManager entityManager;

    public List<Publisher> findALl(){
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Publisher");

        List<Publisher> publishers = query.getResultList();

        return publishers;
    }

    public Publisher find(int id){
        Session session = entityManager.unwrap(Session.class);

        Query<Publisher> query = session.createQuery("from Publisher p where p.id=:pubId ");
        query.setParameter("pubId",id);

        Publisher publisher = query.getSingleResult();

        return publisher;
    }

    public void add(Publisher publisher){
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(publisher);

    }

    public void delete(int id){
        Session session = entityManager.unwrap(Session.class);

        Publisher publisher = session.get(Publisher.class,id);
        for(var game:publisher.getGames()){
            game.setPublisher(null);
        }
        publisher.setGames(null);

        session.delete(publisher);


    }

}
