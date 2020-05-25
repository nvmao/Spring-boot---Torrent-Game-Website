package com.mao.springboot.gameshop.Dao;
import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Entity.Publisher;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.ArrayList;
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

    public  List<Game> findAllSortedByNew(int page,int order,String genre,int limit){

        int itemPerPage = limit;
        int offset = (page - 1) * itemPerPage;
        Session session = entityManager.unwrap(Session.class);

        List<String> genreList = getGenreListFromString(genre);

        List<Game> games;
        String queryString;


        Query query  ;
        if(genre.equals("all")){
            queryString = "from Game order by id desc";

            if(order==-1){
                queryString  = "from Game order by id";
            }
            query  = session.createQuery(queryString);
            query.setFirstResult(offset);
            query.setMaxResults(itemPerPage);
            games = query.getResultList();
        }else{

            queryString = "select g from Game g join g.gernes ger where ger.name in :input group by g.id order by g.id desc ";

            if(order==-1){
                queryString = "select g from Game g join g.gernes ger where ger.name in :input group by g.id order by g.id ";
            }
            query  = session.createQuery(queryString);
            query.setParameterList("input",genreList);
            query.setFirstResult(offset);
            query.setMaxResults(itemPerPage);

            if(genreList.size() == 1){
                games = query.getResultList();
            }
            else{
                games = getConjuntGerne(query.getResultList(),genreList);
            }

        }




        return games;
    }

    public  List<Game> findAllSortedByLove(int page,int order,String genre,int limit){

        int itemPerPage = limit;
        int offset = (page - 1) * itemPerPage;
        Session session = entityManager.unwrap(Session.class);

        List<String> genreList = getGenreListFromString(genre);

        List<Game> games;
        String queryString;


        Query query  ;
        if(genre.equals("all")){
            queryString = "from Game order by loves.size desc";

            if(order==-1){
                queryString  = "from Game order by loves.size";
            }
            query  = session.createQuery(queryString);
            query.setFirstResult(offset);
            query.setMaxResults(itemPerPage);
            games = query.getResultList();
        }else{

            queryString = "select g from Game g join g.gernes ger where ger.name in :input group by g.id order by g.loves.size desc ";

            if(order==-1){
                queryString = "select g from Game g join g.gernes ger where ger.name in :input group by g.id order by g.loves.size ";
            }
            query  = session.createQuery(queryString);
            query.setParameterList("input",genreList);
            query.setFirstResult(offset);
            query.setMaxResults(itemPerPage);

            if(genreList.size() == 1){
                games = query.getResultList();
            }
            else{
                games = getConjuntGerne(query.getResultList(),genreList);
            }

        }




        return games;
    }

    private List<String> getGenreListFromString(String genre){
        String[] str = genre.split(",");
        List<String> genreList = new ArrayList<String>();
        for(var s : str){
            genreList.add(s);
        }
        return genreList;
    }

    private List<Game> getConjuntGerne(List<Game> games,List<String> genreList){

        List<Game> result = new ArrayList<>();

        for(var game:games){

           int count = genreList.size();

            for(var genre: genreList){
                if(game.getListGenreName().contains(genre)){
                    count--;
                }
            }
            if(count==0){
                result.add(game);
            }

        }
        return result;
    }

    public  List<Game> findAllSortedByDownload(int page,int order,String genre,int limit){

        int itemPerPage = limit;
        int offset = (page - 1) * itemPerPage;
        Session session = entityManager.unwrap(Session.class);

        List<String> genreList = getGenreListFromString(genre);

        List<Game> games;
        String queryString;


        Query query  ;
        if(genre.equals("all")){
            queryString = "from Game order by downloadCount desc";

            if(order==-1){
                queryString  = "from Game order by downloadCount";
            }
            query  = session.createQuery(queryString);
            query.setFirstResult(offset);
            query.setMaxResults(itemPerPage);
            games = query.getResultList();
        }else{

            queryString = "select g from Game g join g.gernes ger where ger.name in :input group by g.id order by g.downloadCount desc ";

            if(order==-1){
                queryString = "select g from Game g join g.gernes ger where ger.name in :input group by g.id order by g.downloadCount ";
            }
            query  = session.createQuery(queryString);
            query.setParameterList("input",genreList);
            query.setFirstResult(offset);
            query.setMaxResults(itemPerPage);

            if(genreList.size() == 1){
                games = query.getResultList();
            }
            else{
                games = getConjuntGerne(query.getResultList(),genreList);
            }

        }




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

    public void deleteGame(int id){
        Session session = entityManager.unwrap(Session.class);

        Game game = session.get(Game.class,id);

       session.delete(game);
    }

}
