package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Dao.GerneDao;
import com.mao.springboot.gameshop.Entity.Gerne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GerneService {

    @Autowired
    private GerneDao gerneDao;

    @Transactional
    public List<Gerne> findAll(){
        return gerneDao.findAll();
    }

    @Transactional
    public void add(Gerne gerne){
        gerneDao.add(gerne);
    }
    @Transactional
    public void delete(int id){
        gerneDao.delete(id);
    }

    @Transactional
    public List<Gerne> find(String genreTags) {
        return gerneDao.find(genreTags);
    }

}
