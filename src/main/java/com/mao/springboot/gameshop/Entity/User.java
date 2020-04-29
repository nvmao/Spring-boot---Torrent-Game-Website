package com.mao.springboot.gameshop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "avatar_image")
    private String avatar;

    @JsonIgnore
    @Column(name = "password")
    private String password;


    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER )
    private List<Authority> authorities;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "user")
//    private List<Comment> comments;
//
//    @JsonManagedReference
//    @OneToMany(mappedBy = "user")
//    private List<Reply> replies;
//
//    public List<Reply> getReplies() {
//        return replies;
//    }
//
//    public void setReplies(List<Reply> replies) {
//        this.replies = replies;
//    }
//
//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
