package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public  class LoginUser {

        public static User getLoginUser(){
            if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails){
                UserDetails userLogin = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = null;
                if(userLogin != null){
                    user = ((MyUserDetails)userLogin).getUser();
                }
                return user;
            }
            return null;
        }

}
