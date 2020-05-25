package com.mao.springboot.gameshop.Controller;

import com.mao.springboot.gameshop.Entity.PasswordResetToken;
import com.mao.springboot.gameshop.Entity.User;
import com.mao.springboot.gameshop.Service.SendMailService;
import com.mao.springboot.gameshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.UUID;

@Controller
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SendMailService sendMailService;

    @GetMapping("/forgot")
    public String forgot(){

        return "/user/forgot-password";
    }

    @GetMapping("/change")
    public String changePass(@RequestParam("token")String token,Model model){

        System.out.println("token: "+token);
        if(validatePasswordResetToken(token)){
            model.addAttribute("token",token);
            return "/user/change-password";
        }

        return "redirect:/login";

    }

    @PostMapping("/save-password")
    public String updatePassowrd(@RequestParam("token")String token,@RequestParam("password")String password){

        PasswordResetToken passwordResetToken = userService.findToken(token);

        if(validatePasswordResetToken(token)){

            User user = passwordResetToken.getUser();

            user.setPassword(passwordEncoder.encode(password));
            userService.saveOrUpdate(user);
            userService.deleteToken(token);

            return "redirect:/login";
        }

        return "redirect:/password/forgot";
    }

    @PostMapping("/reset")
    public String resetPass(@RequestParam("email")String email, Model model, HttpServletRequest request){

        try {

            User user = userService.findByEmail(email);
            String token = UUID.randomUUID().toString();

            userService.createPasswordResetToken(user,token);

            String resetPassLink = "http://localhost:8080/password/change?token="+token;

            sendMailService.sendSimpleMessage("newman33399@gmail.com","[M-GAME] Reset your password"
                    ,"Click here to reset your password: "+resetPassLink);

        }catch (Exception e){
            System.out.println("error: "+e);

            model.addAttribute("messageR","Email address does not exist");
            return "/user/forgot-password";
        }


        model.addAttribute("messageG","Please check your email");
        return "/user/forgot-password";

    }

    public boolean validatePasswordResetToken(String token) {
        PasswordResetToken passToken;
        try {
            passToken = userService.findToken(token);
        }catch (Exception e){
            System.out.println("error: "+e);
            return false;
        }

        if(isTokenExpired(passToken)){
            System.out.println("token expired");
            return false;
        }

        return true;

    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpireDate().before(cal.getTime());
    }

}
