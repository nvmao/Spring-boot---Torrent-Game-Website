//package com.mao.springboot.gameshop.Controller;
//
//
//import com.mao.springboot.gameshop.Entity.UserInfo;
//import com.mao.springboot.gameshop.Service.FacebookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.social.facebook.api.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.view.RedirectView;
//
//@Controller
//public class FacebookController {
//
//    @Autowired
//    private FacebookService facebookService;
//
//    @GetMapping("/facebookLogin")
//    public RedirectView facebookLogin() {
//        RedirectView redirectView = new RedirectView();
//        String url = facebookService.facebookLogin();
//
//        System.out.println(url);
//
//        redirectView.setUrl(url);
//        return redirectView;
//    }
//
//    @GetMapping("/facebook")
//    public String facebook(@RequestParam("code") String code) {
//        String accessToken = facebookService.getFacebookAccessToken(code);
//        return "redirect:/facebookProfileData/"+accessToken;
//    }
//
//    @GetMapping("/facebookProfileData/{accessToken:.+}")
//    public String facebookProfileData(@PathVariable String accessToken, Model model){
//        User user = facebookService.getFacebookUserProfile(accessToken);
//
//        UserInfo userInfo = new UserInfo(user.getFirstName(),user.getLastName(),null);
//
//        model.addAttribute("user",userInfo);
//        return "/userprofile";
//    }
//
//}