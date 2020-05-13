package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Dao.LoveDao;
import com.mao.springboot.gameshop.Entity.Love;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
public class LoveService {

    @Autowired
    private LoveDao loveDao;

    @Transactional
    public long countLove(int gameId){
        return loveDao.countLove(gameId);
    }
    @Transactional
    public boolean isLoved(int userId,int gameId){
        return loveDao.isLoved(userId,gameId);
    }
    @Transactional
    public void add(Love love){
       loveDao.add(love);
    }
    @Transactional
    public void remove(int userId,int gameId){
        loveDao.remove(userId,gameId);
    }

}
