package hr.fer.ppj.projekt.hzj.core.models.business;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ANTE on 21.5.2016..
 */

// PRESJECNA TABLICA (ima surogat kljuc 'ID')
public class Favorite {
    // private fields
    @SerializedName("ID")
    @Expose
    private int id;
    @SerializedName("KorisnikID")
    @Expose
    private int userId;
    @SerializedName("VideoID")
    @Expose
    private int videoId;

    // references to other tables
    private User user;
    private Video video;

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    // getters and setters for references
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    // constructors
    public Favorite() {

    }

    public Favorite(int id, int userId, int videoId) {
        this.id = id;
        this.userId = userId;
        this.videoId = videoId;
    }
}
