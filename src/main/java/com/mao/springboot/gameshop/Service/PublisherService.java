package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Dao.PublisherDao;
import com.mao.springboot.gameshop.Entity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PublisherService {

    @Autowired
    PublisherDao publisherDao;

    @Transactional
    public List<Publisher> findAll(){
        List<Publisher> publishers = publisherDao.findALl();
        return publishers;
    }

    @Transactional
    public Publisher find(int id){
        return publisherDao.find(id);
    }

}
