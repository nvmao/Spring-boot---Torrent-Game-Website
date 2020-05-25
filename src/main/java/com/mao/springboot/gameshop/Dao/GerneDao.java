package com.mao.springboot.gameshop.Dao;

import com.mao.springboot.gameshop.Entity.Gerne;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
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

    @Transactional
    public void add(Gerne gerne){
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(gerne);

    }

    @Transactional
    public void delete(int id){
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("delete from Gerne where id=:id");
        query.setParameter("id",id);

        query.executeUpdate();
    }
}
