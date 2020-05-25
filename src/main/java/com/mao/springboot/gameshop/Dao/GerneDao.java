package com.mao.springboot.gameshop.Dao;

import com.mao.springboot.gameshop.Entity.Gerne;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GerneDao {

    @Autowired
    private EntityManager entityManager;

    public List<Gerne> findAll(){

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Gerne");

        return query.getResultList();
    }

    public void add(Gerne gerne){
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(gerne);

    }

    public void delete(int id){
        Session session = entityManager.unwrap(Session.class);

        Gerne gerne = session.get(Gerne.class,id);

        for(var game:gerne.getGames()){
            game.getGernes().remove(gerne);
        }
        gerne.setGames(null);

        session.delete(gerne);
    }


    public List<Gerne> find(String genreTags){
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Gerne where name in :tags");
        query.setParameterList("tags",getGenreListFromString(genreTags));

        return query.getResultList();
    }

    private List<String> getGenreListFromString(String genre){
        String[] str = genre.split(",");
        List<String> genreList = new ArrayList<String>();
        for(var s : str){
            genreList.add(s);
        }
        return genreList;
    }

}
