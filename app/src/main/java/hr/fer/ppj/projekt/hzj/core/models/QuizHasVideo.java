package hr.fer.ppj.projekt.hzj.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ANTE on 21.5.2016..
 */

// PRESJECNA TABLICA (ima surogat kljuc 'ID')
public class QuizHasVideo {
    // private fields
    @SerializedName("ID")
    @Expose
    private int id;
    @SerializedName("KvizID")
    @Expose
    private int quizId;
    @SerializedName("VideoID")
    @Expose
    private int videoId;

    // references to other tables
    private Video video;
    private Quiz quiz;

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    // getters and setters for references
    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    // constructors
    public QuizHasVideo() {

    }

    public QuizHasVideo(int id, int quizId, int videoId) {
        this.id = id;
        this.quizId = quizId;
        this.videoId = videoId;
    }
}
