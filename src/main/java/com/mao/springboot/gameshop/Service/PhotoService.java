package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Dao.GameDao;
import com.mao.springboot.gameshop.Dao.PhotoDao;
import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PhotoService {
    @Autowired
    PhotoDao photoDao;

    @Transactional
    public List<Photo> findAll(){
        List<Photo> photos = photoDao.findAll();

        return photos;
    }

    @Transactional
    public void add(Photo photo){
        photoDao.add(photo);
    }

    @Transactional
    public Photo find(int id){
        return photoDao.find(id);
    }
}
