package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Dao.UserDao;
import com.mao.springboot.gameshop.Entity.Authority;
import com.mao.springboot.gameshop.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public List<User> findAll(){
        return userDao.findAll();
    }
}
