package com.mao.springboot.gameshop.Dao;

import com.mao.springboot.gameshop.Entity.Authority;
import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    EntityManager entityManager;

    public User findUserByName(String username){
        Session session = entityManager.unwrap(Session.class);

        Query<User> query = session.createQuery("from User u where u.userName=:username ");
        query.setParameter("username",username);

        User user = query.getSingleResult();

        return user;
    }

    public List<User> findAll(){
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from User");

        List<User> users = query.getResultList();

        return users;
    }

    public void saveOrUpdate(User user){
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(user);
    }

    public void addAuthority(Authority authority){
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(authority);
    }


}
