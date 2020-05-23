package com.mao.springboot.gameshop.Dao;
import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Entity.Publisher;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class GameDao {

    @Autowired
    EntityManager entityManager;

    public List<Game> findAll(){

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Game");

        List<Game> games = query.getResultList();

        return games;
    }

    public void add(Game game){
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(game);
    }

    public Game find(int id){
        Session session = entityManager.unwrap(Session.class);

        Query<Game> query = session.createQuery("from Game p where p.id=:gameId ");
        query.setParameter("gameId",id);

        Game game = query.getSingleResult();

        return game;
    }

    public  List<Game> findAll(int page){
        int itemPerPage = 28;
        int offset = (page - 1) * itemPerPage;

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Game ");

        query.setFirstResult(offset);
        query.setMaxResults(itemPerPage);

        List<Game> games = query.getResultList();

        return games;
    }

    public List<Game> search(String str){
        Session session = entityManager.unwrap(Session.class);


        Query query = session.createQuery("from Game where name like :sf");
        query.setParameter("sf","%"+str+"%");

        List<Game> games = query.getResultList();
        return games;
    }

    public  List<Game> findAllSortedByNew(int page,int order){

        int itemPerPage = 28;
        int offset = (page - 1) * itemPerPage;
        Session session = entityManager.unwrap(Session.class);

        String queryString;
        if(order == 1){
          queryString  = "from Game order by id desc ";
        }
        else{
            queryString  = "from Game order by id ";
        }

        Query query = session.createQuery(queryString);

        query.setFirstResult(offset);
        query.setMaxResults(itemPerPage);

        List<Game> games = query.getResultList();

        return games;
    }

    public  List<Game> findAllSortedByLove(int page,int order){

        int itemPerPage = 28;
        int offset = (page - 1) * itemPerPage;
        Session session = entityManager.unwrap(Session.class);

        String queryString  = "from Game order by loves.size desc";

        if(order==-1){
            queryString  = "from Game order by loves.size";
        }

        Query query = session.createQuery(queryString);

        query.setFirstResult(offset);
        query.setMaxResults(itemPerPage);

        List<Game> games = query.getResultList();

        return games;
    }

    public  List<Game> findAllSortedByDownload(int page,int order){

        int itemPerPage = 28;
        int offset = (page - 1) * itemPerPage;
        Session session = entityManager.unwrap(Session.class);

        String queryString  = "from Game order by downloadCount desc";

        if(order==-1){
            queryString  = "from Game order by downloadCount";
        }

        Query query = session.createQuery(queryString);

        query.setFirstResult(offset);
        query.setMaxResults(itemPerPage);

        List<Game> games = query.getResultList();

        return games;
    }

    public  List<Game> findAllSortedByComment(int page,int order){

        int itemPerPage = 28;
        int offset = (page - 1) * itemPerPage;
        Session session = entityManager.unwrap(Session.class);

        String queryString  = "from Game order by downloadCount desc";

        if(order==-1){
            queryString  = "from Game order by downloadCount desc";
        }

        Query query = session.createQuery(queryString);

        query.setFirstResult(offset);
        query.setMaxResults(itemPerPage);

        List<Game> games = query.getResultList();

        return games;
    }

    public Long countGames(){
        Session session = entityManager.unwrap(Session.class);

        Query<Long> query = session.createQuery("select count(*) from Game");

        Long count = query.uniqueResult();

        return count;
    }

}
