package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Dao.GameDao;
import com.mao.springboot.gameshop.Entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public void add(Game game){
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

}
