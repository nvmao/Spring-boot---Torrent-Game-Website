package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Dao.UserDao;
import com.mao.springboot.gameshop.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByName(username);

        if(user == null){
            throw new UsernameNotFoundException("not found: " + username);
        }

        return new MyUserDetails(user);

    }
}
