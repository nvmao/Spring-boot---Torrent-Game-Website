package com.mao.springboot.gameshop.Dao;

import com.mao.springboot.gameshop.Entity.Authority;
import com.mao.springboot.gameshop.Entity.Game;
import com.mao.springboot.gameshop.Entity.PasswordResetToken;
import com.mao.springboot.gameshop.Entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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

    public User findUserByEmail(String email){
        Session session = entityManager.unwrap(Session.class);

        Query<User> query = session.createQuery("from User u where u.email=:email ");
        query.setParameter("email",email);

        User user = query.getSingleResult();

        return user;
    }

    public void saveResetPasswordToken(PasswordResetToken token){
        Session session = entityManager.unwrap(Session.class);
        session.save(token);

    }

    public PasswordResetToken findToken(String token){
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from PasswordResetToken where token=:token");
        query.setParameter("token",token);

        return (PasswordResetToken) query.getSingleResult();
    }

    public List<User> findAll(){
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from User ");

        List<User> users = query.getResultList();

        return users;
    }

    public void saveOrUpdate(User user) {

        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(user);

    }

    public void deleteToken(String token){
        Session session = entityManager.unwrap(Session.class);

        PasswordResetToken passwordResetToken = findToken(token);

        passwordResetToken.setUser(null);

        session.remove(passwordResetToken);

    }

    public void addAuthority(Authority authority){
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(authority);
    }


    public User findUserById(int id) {
        Session session = entityManager.unwrap(Session.class);

        Query<User> query = session.createQuery("from User u where u.id=:id ");
        query.setParameter("id",id);

        User user = query.getSingleResult();

        return user;
    }
}
