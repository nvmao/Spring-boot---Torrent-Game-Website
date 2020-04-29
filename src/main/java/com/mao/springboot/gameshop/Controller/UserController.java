package com.mao.springboot.gameshop.Controller;

import com.mao.springboot.gameshop.Entity.Authority;
import com.mao.springboot.gameshop.Entity.User;
import com.mao.springboot.gameshop.Service.FileService;
import com.mao.springboot.gameshop.Service.LoginUser;
import com.mao.springboot.gameshop.Service.MyUserDetails;
import com.mao.springboot.gameshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @GetMapping("/signup")
    public String signUp(Model model){

        model.addAttribute("user",new User());

        return "user/signup";
    }

    @PostMapping("/signup")
    public String signUp(HttpServletRequest request,@ModelAttribute User user, @RequestParam("avatarFile")MultipartFile avatar){

        try {
            if(!avatar.isEmpty()){
                fileService.saveImage(avatar);
                user.setAvatar("/uploads/img/"+avatar.getOriginalFilename());
            }

            userService.add(user);
            Authority authority = new Authority("ROLE_USER");
            authority.setUser(user);
            userService.addAuthority(authority);

            authenticateUserAndSetSession(user,request);

        }catch (Exception e){
            System.out.println(e);
        }

        return "redirect:/games";
    }

    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        String username = user.getUserName();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }


    @GetMapping("/profile")
    public String profile(Model model){

        User user = LoginUser.getLoginUser();

        model.addAttribute("user",user);

        return "user/user-profile";
    }

}
