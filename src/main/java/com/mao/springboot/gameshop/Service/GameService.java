package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Dao.GameDao;
import com.mao.springboot.gameshop.Entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @Autowired
    GameDao gameDao;

    @Transactional
    public List<Game> findAll(){
        List<Game> games = gameDao.findAll();

        return games;
    }

    @Transactional
    public List<Game> findAll(int page){
        List<Game> games = gameDao.findAll(page);

        return games;
    }

    public List<Game> getRandomGame(int total){
        List<Game> games = findAll();
        List<Game> randomGames = new ArrayList<>();
        for(int i = 0;i<total;i++){
            int random = (int)(Math.random() * games.size());

            while (randomGames.contains(games.get(random))){
                random = (int)(Math.random() * games.size());
            }
            randomGames.add(games.get(random));
        }
        return randomGames;
    }

    // mvc

    @Transactional
    public List<Game> search(String str) {
        return gameDao.search(str);
    }

    @Transactional
    public void save(Game game){
        gameDao.add(game);
    }

    @Transactional
    public Game find(int id){
        return gameDao.find(id);
    }

    @Transactional
    public Long countGame(){
        return gameDao.countGames();
    }
// data access object
    @Transactional
    public  List<Game> findAllSortedByNew(int page,int order,String genre,int limit){
        return gameDao.findAllSortedByNew(page,order,genre,limit);
    }

    @Transactional
    public  List<Game> findAllSortedByLove(int page,int order,String genre,int limit) {
        return gameDao.findAllSortedByLove(page,order,genre,limit);
    }

    @Transactional
    public  List<Game> findAllSortedByDownload(int page,int order,String genre,int limit) {
        return gameDao.findAllSortedByDownload(page,order,genre,limit);
    }

    @Transactional
    public void delete(int id){
        gameDao.deleteGame(id);
    }

}





















