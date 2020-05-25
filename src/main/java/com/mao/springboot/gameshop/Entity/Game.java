package com.mao.springboot.gameshop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int id;

    @Column(name = "game_name")
    private String name;

    @Column(name = "download_link")
    private String downloadLink;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "steam_review")
    private float steamReview;

    @Column(name = "poster_photo")
    private String posterPhoto;

    @Column(name = "hover_photo")
    private String hoverPhoto;

    private String description;

    @Column(name = "download_count")
    private int downloadCount;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "game",cascade= {CascadeType.ALL})
    private List<Photo> photos;

    @OneToMany(mappedBy = "game",cascade= {CascadeType.ALL})
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "game",cascade= {CascadeType.ALL})
    private List<Love> loves;

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "game_gerne",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "gerne_id")}
    )
    private List<Gerne> gernes;

    @Transient
    private String url;

    public int getCommentCount(){
        int count = 0;
        for(var c : comments){
            count++;
            count += c.getReplies().size();
        }
        return  count;
    }

    public Game() {
    }


    public List<Gerne> getGernes() {
        return gernes;
    }

    public void setGernes(List<Gerne> gernes) {
        this.gernes = gernes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Love> getLoves() {
        return loves;
    }

    public void setLoves(List<Love> loves) {
        this.loves = loves;
    }

    public int getLoveCount() {
        return  loves.size();
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getHoverPhoto() {
        return hoverPhoto;
    }

    public void setHoverPhoto(String hoverPhoto) {
        this.hoverPhoto = hoverPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getSteamReview() {
        return steamReview;
    }

    public void setSteamReview(float steamReview) {
        this.steamReview = steamReview;
    }

    public String getPosterPhoto() {
        return posterPhoto;
    }

    public void setPosterPhoto(String posterPhoto) {
        this.posterPhoto = posterPhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getListGenreName(){
        List<String> genreName = new ArrayList<>();
        for(var g : gernes){
            genreName.add(g.getName());
        }
        return genreName;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gerne='"+ gernes+'\''+
                '}';
    }
}
