package hr.fer.ppj.projekt.hzj.core.models;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ANTE on 19.5.2016..
 */
public class Video {
    // private fields
    @SerializedName("ID")
    @Expose
    private int id;
    @SerializedName("Naziv")
    @Expose
    private String name;
    @SerializedName("URL")
    @Expose
    private String url;
    private Uri downloadedVideo;
    @SerializedName("SkupinaID")
    @Expose
    private int sectionId;


    // private Drawable backgroundImage;


    // references to other tables
    private List<Favorite> favoriteList;
    private Section section;
    private List<QuizHasVideo> quizHasVideoList;

    // getters and setters
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Uri getDownloadedVideo() {
        return downloadedVideo;
    }

    public void setDownloadedVideo(Uri downloadedVideo) {
        this.downloadedVideo = downloadedVideo;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    // getters and setters for references
    public List<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public List<QuizHasVideo> getQuizHasVideoList() {
        return quizHasVideoList;
    }

    public void setQuizHasVideoList(List<QuizHasVideo> quizHasVideoList) {
        this.quizHasVideoList = quizHasVideoList;
    }

    /*
    public Drawable getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Drawable backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
    */

    // constructors
    public Video() {

    }

    public Video(int id, String name, String url, int sectionId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.sectionId = sectionId;
    }

    // methods
    // we get videos in most part of app as list, only when we click one we send request to download
    public void downloadVideoFromURL() {
        String neki_url = "";
        this.downloadedVideo = Uri.parse(neki_url + this.url);
    }
}
