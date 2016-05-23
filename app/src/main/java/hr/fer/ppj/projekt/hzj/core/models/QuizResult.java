package hr.fer.ppj.projekt.hzj.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by ANTE on 21.5.2016..
 */

// PRESJECNA TABLICA (ima surogat kljuc 'ID')
public class QuizResult {
    // private fields
    @SerializedName("ID")
    @Expose
    private int id;
    @SerializedName("KorisnikID")
    @Expose
    private int userId;
    @SerializedName("KvizID")
    @Expose
    private int quizId;
    @SerializedName("BrojSavladanihPitalica")
    @Expose
    private int numberOfSuccessfulAnswers;
    @SerializedName("ZadnjeOdigrano")
    @Expose
    private Date lastPlayed;

    // references to other tables
    private User user;
    private Quiz quiz;

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

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getNumberOfSuccessfulAnswers() {
        return numberOfSuccessfulAnswers;
    }

    public void setNumberOfSuccessfulAnswers(int numberOfSuccessfulAnswers) {
        this.numberOfSuccessfulAnswers = numberOfSuccessfulAnswers;
    }

    public Date getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Date lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    // getters and setters for references
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    // constructors
    public QuizResult() {

    }

    public QuizResult(int id, int userId, int quizId, int numberOfSuccessfulAnswers, Date lastPlayed) {
        this.id = id;
        this.userId = userId;
        this.quizId = quizId;
        this.numberOfSuccessfulAnswers = numberOfSuccessfulAnswers;
        this.lastPlayed = lastPlayed;
    }
}
