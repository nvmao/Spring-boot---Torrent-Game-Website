package com.mao.springboot.gameshop.Dao;

import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Entity.Photo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PhotoDao {
    @Autowired
    EntityManager entityManager;

    public List<Photo> findAll(){

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Photo");

        List<Photo> photos = query.getResultList();

        return photos;
    }

    public void add(Photo photo){
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(photo);
    }


    public void delete(int id){
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("delete from Photo where id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    public Photo find(int id){
        Session session = entityManager.unwrap(Session.class);

        Query<Photo> query = session.createQuery("from Photo p where p.id=:photoId ");
        query.setParameter("photoId",id);

        Photo photo = query.getSingleResult();

        return photo;
    }
}
