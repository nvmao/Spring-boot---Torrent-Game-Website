package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Dao.UserDao;
import com.mao.springboot.gameshop.Entity.Authority;
import com.mao.springboot.gameshop.Entity.PasswordResetToken;
import com.mao.springboot.gameshop.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Transactional
    public User findUserByName(String username){
        return userDao.findUserByName(username);
    }
    @Transactional
    public User findUserById(int id){
        return userDao.findUserById(id);
    }

    @Transactional
    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
    }

    @Transactional
    public void addAuthority(Authority authority){
        userDao.addAuthority(authority);
    }

    @Transactional
    public void createPasswordResetToken(User user,String token){
        PasswordResetToken myToken = new PasswordResetToken(user,token);
        userDao.saveResetPasswordToken(myToken);
    }

    @Transactional
    public void deleteToken(String token){
        userDao.deleteToken(token);
    }

    @Transactional
    public PasswordResetToken findToken(String token){
        return userDao.findToken(token);
    }

    @Transactional
    public List<User> findAll(){
        return userDao.findAll();
    }

    @Transactional
    public User findByEmail(String email){
        return userDao.findUserByEmail(email);
    }
}
