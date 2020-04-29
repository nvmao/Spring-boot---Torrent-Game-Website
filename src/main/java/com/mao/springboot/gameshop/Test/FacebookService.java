//package com.mao.springboot.gameshop.Service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.ConnectionKey;
//import org.springframework.social.connect.UserProfile;
//import org.springframework.social.facebook.api.Facebook;
//import org.springframework.social.facebook.api.User;
//import org.springframework.social.facebook.api.impl.FacebookTemplate;
//import org.springframework.social.facebook.connect.FacebookConnectionFactory;
//import org.springframework.social.oauth2.AccessGrant;
//import org.springframework.social.oauth2.OAuth2Parameters;
//import org.springframework.stereotype.Service;
//
//@Service
//public class FacebookService {
//
//    @Value("${spring.social.facebook.app-id}")
//    private String facebookId;
//    @Value("${spring.social.facebook.app-secret}")
//    private String facebookSecret;
//
//
//    private FacebookConnectionFactory createFacebookConnection(){
//
//        return new FacebookConnectionFactory(facebookId,facebookSecret);
//    }
//
//    public String facebookLogin() {
//        OAuth2Parameters parameters = new OAuth2Parameters();
//        parameters.setRedirectUri("http://localhost:8080/facebook");
//        parameters.setScope("public_profile,email,user_photos");
//
//        return createFacebookConnection().getOAuthOperations().buildAuthenticateUrl(parameters);
//    }
//
//    public String getFacebookAccessToken(String code) {
//
//        return createFacebookConnection().getOAuthOperations()
//                .exchangeForAccess(code,"http://localhost:8080/facebook",null).getAccessToken();
//    }
//
//    public User getFacebookUserProfile(String accessToken) {
//        Facebook facebook = new FacebookTemplate(accessToken);
//
//       String field[] = {"id","first_name","last_name","email"};
//
//        org.springframework.social.facebook.api.User user = facebook.fetchObject("me",org.springframework.social.facebook.api.User.class,field);
//
//
//        return user;
//    }
//}
