package com.mao.springboot.gameshop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private int id;

    @Column(name = "publisher_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "icon_photo")
    private String icon_photo;

    @Column(name = "bg_photo")
    private String bg_photo;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher",cascade= {CascadeType.ALL})
    private List<Game> games;

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Publisher() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon_photo() {
        return icon_photo;
    }

    public void setIcon_photo(String icon_photo) {
        this.icon_photo = icon_photo;
    }

    public String getBg_photo() {
        return bg_photo;
    }

    public void setBg_photo(String bg_photo) {
        this.bg_photo = bg_photo;
    }
}
