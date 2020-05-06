package com.mao.springboot.gameshop.Controller;

import com.mao.springboot.gameshop.Entity.Authority;
import com.mao.springboot.gameshop.Entity.User;
import com.mao.springboot.gameshop.Service.FileService;
import com.mao.springboot.gameshop.Service.LoginUser;
import com.mao.springboot.gameshop.Service.UserService;
import com.mao.springboot.gameshop.Util.SimpleException;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/signup")
    public String signUp(Model model){

        model.addAttribute("user",new User());

        return "user/signup";
    }

    @PostMapping("/signup")
    public String signUp(HttpServletRequest request, @ModelAttribute User user,
                         @RequestParam("rePassword") String rePassword,
                         @RequestParam("avatarFile")MultipartFile avatar,Model model){

        List<String> messages = new ArrayList<>();

        try {
            if(!avatar.isEmpty()){
                fileService.saveImage(avatar);
                user.setAvatar("/uploads/img/"+avatar.getOriginalFilename());
            }

            if(!user.getPassword().equals(rePassword)){
                throw new SimpleException("password not match when you type it again");
            }

            userService.saveOrUpdate(user);
            Authority authority = new Authority("ROLE_USER");
            authority.setUser(user);
            userService.addAuthority(authority);

            authenticateUserAndSetSession(user,request);

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
