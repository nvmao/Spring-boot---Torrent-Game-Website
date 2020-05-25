package com.mao.springboot.gameshop.Controller;

import com.mao.springboot.gameshop.Entity.Authority;
import com.mao.springboot.gameshop.Entity.User;
import com.mao.springboot.gameshop.Service.FileService;
import com.mao.springboot.gameshop.Service.LoginUser;
import com.mao.springboot.gameshop.Service.UserService;
import com.mao.springboot.gameshop.Util.SimpleException;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signUp(Model model){

        model.addAttribute("user",new User());

        return "user/signup";
    }

    @PostMapping("/signup")
    public String signUp(HttpServletRequest  request, @ModelAttribute User user,
                         @RequestParam("rePassword") String rePassword,
                         @RequestParam("avatarFile")MultipartFile avatar,Model model){

        List<String> messages = new ArrayList<>();

        try {
            user.setAvatar("/uploads/img/cat.jpg");
            if(!avatar.isEmpty()){
                fileService.saveImage(avatar);
                user.setAvatar("/uploads/img/"+avatar.getOriginalFilename());
            }

            if(!user.getPassword().equals(rePassword)){
                throw new SimpleException("password not match when you type it again");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userService.saveOrUpdate(user);
            Authority authority = new Authority("ROLE_USER");
            authority.setUser(user);
            userService.addAuthority(authority);


        }catch (Exception e) {
            if(e instanceof ConstraintViolationException){
                ConstraintViolationException cvE = (ConstraintViolationException) e;
                for (var cv : cvE.getConstraintViolations()) {
                    messages.add(cv.getMessage());
                }
            }
            if(e instanceof DataIntegrityViolationException){
                messages.add("username '"+user.getUserName() +"' have been taken");
            }
            if(e instanceof SimpleException){
                messages.add(e.getMessage());
            }
        }

        if(messages.size() >0){
            model.addAttribute("messages",messages);
            return "user/signup";
        }
        else {
            messages = null;
        }

//        try {
//            request.login(user.getUserName(),user.getPassword());
//        }catch (Exception e){
//            System.out.println("error: "+e);
//        }

        return "redirect:/games";
    }

    @GetMapping("/profile")
    public String profile(Model model){

        User user = LoginUser.getLoginUser();

        model.addAttribute("user",user);

        return "user/user-profile";
    }

}
