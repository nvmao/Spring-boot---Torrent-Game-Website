//package com.mao.springboot.gameshop.Controller;
//
//import com.mao.springboot.gameshop.Entity.UserInfo;
//import com.mao.springboot.gameshop.Service.GoogleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.social.facebook.api.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.view.RedirectView;
//import org.springframework.social.google.api.plus.Person;
//
//@Controller
//public class GoogleController {
//
//    @Autowired
//    GoogleService googleService;
//
//    @GetMapping("/googleLogin")
//    public RedirectView googleLogin() {
//        RedirectView redirectView = new RedirectView();
//        String url = googleService.googleLogin();
//
//        System.out.println(url);
//
//        redirectView.setUrl(url);
//        return redirectView;
//    }
//
//    @GetMapping("/google")
//    public String google(@RequestParam("code") String code) {
//        String accessToken = googleService.getGoogleAccessToken(code);
//        return "redirect:/googleprofiledata/"+accessToken;
//    }
//
//    @GetMapping("/googleprofiledata/{accessToken:.+}")
//    public String googleProfileData(@PathVariable String accessToken, Model model){
//        Person user = googleService.getGoogleUserProfile(accessToken);
//
//        UserInfo userInfo = new UserInfo(user.getGivenName(),user.getFamilyName(),user.getImageUrl());
//        System.out.println(userInfo);
//
//        model.addAttribute("user",userInfo);
//        return "/userprofile";
//    }
//
//}
