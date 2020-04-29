//package com.mao.springboot.gameshop.Service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.social.facebook.api.Facebook;
//import org.springframework.social.facebook.api.User;
//import org.springframework.social.facebook.api.impl.FacebookTemplate;
//import org.springframework.social.facebook.connect.FacebookConnectionFactory;
//import org.springframework.social.google.api.impl.GoogleTemplate;
//import org.springframework.social.google.connect.GoogleConnectionFactory;
//import org.springframework.social.oauth2.OAuth2Parameters;
//import org.springframework.stereotype.Service;
//import org.springframework.social.google.api.plus.Person;
//import org.springframework.social.google.api.Google;
//
//@Service
//public class GoogleService {
//
//    @Value("${spring.social.google.app-id}")
//    private String googleId;
//    @Value("${spring.social.google.app-secret}")
//    private String googleSecret;
//
//
//    private GoogleConnectionFactory createGoogleConnection(){
//
//        return new GoogleConnectionFactory(googleId,googleSecret);
//    }
//
//    public String googleLogin() {
//        OAuth2Parameters parameters = new OAuth2Parameters();
//        parameters.setRedirectUri("http://localhost:8080/google");
//        parameters.setScope("profile");
//
//        return createGoogleConnection().getOAuthOperations().buildAuthenticateUrl(parameters);
//    }
//
//    public String getGoogleAccessToken(String code) {
//
//        return createGoogleConnection().getOAuthOperations()
//                .exchangeForAccess(code,"http://localhost:8080/google",null).getAccessToken();
//    }
//
//    public Person getGoogleUserProfile(String accessToken) {
//        Google google  = new GoogleTemplate(accessToken);
//
//        String field[] = {"id","first_name","last_name","email"};
//
//        Person person = google.plusOperations().getGoogleProfile();
//
//        return person;
//    }
//}
