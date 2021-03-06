package com.mao.springboot.gameshop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min=2,max=12,message = "username size must be from 2 to 12")
    @Column(name = "username")
    private String userName;

    @Column(name = "avatar_image")
    private String avatar;

    @Column(name = "status")
    private boolean status;

    //    \\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Size(min = 1,message = "password is required")
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER )
    private List<Authority> authorities;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

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
