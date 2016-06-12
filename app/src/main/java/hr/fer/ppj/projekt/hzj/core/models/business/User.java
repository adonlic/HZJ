package hr.fer.ppj.projekt.hzj.core.models.business;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by ANTE on 19.5.2016..
 */
public class User {
    // private fields
    @SerializedName("ID")
    @Expose
    private int id;
    @SerializedName("KorisnickoIme")
    @Expose
    private String username;
    @SerializedName("Lozinka")
    @Expose
    private String password;
    @SerializedName("Ime")
    @Expose
    private String name;
    @SerializedName("Prezime")
    @Expose
    private String surname;
    // in database attribute 'active' is type of 'bit'
    @SerializedName("Aktivan")
    @Expose
    private boolean active;
    @SerializedName("USustavuOd")
    @Expose
    private Date inSystemFrom;
    @SerializedName("ZadnjaAktivnost")
    @Expose
    private Date lastActivity;

    // references to other tables
    private List<Statistics> statisticsList;
    private List<Achievement> achievementList;
    private List<Favorite> favoriteList;
    private List<QuizResult> quizResultList;

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getInSystemFrom() {
        return inSystemFrom;
    }

    public void setInSystemFrom(Date inSystemFrom) {
        this.inSystemFrom = inSystemFrom;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }

    // getters and setters for references
    public List<Statistics> getStatisticsList() {
        return statisticsList;
    }

    public void setStatisticsList(List<Statistics> statisticsList) {
        this.statisticsList = statisticsList;
    }

    public List<Achievement> getAchievementList() {
        return achievementList;
    }

    public void setAchievementList(List<Achievement> achievementList) {
        this.achievementList = achievementList;
    }

    public List<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

    public List<QuizResult> getQuizResultList() {
        return quizResultList;
    }

    public void setQuizResultList(List<QuizResult> quizResultList) {
        this.quizResultList = quizResultList;
    }

    // constructors
    public User() {

    }

    public User(int id, String username, String password, String name, String surname,
                boolean active, Date inSystemFrom, Date lastActivity) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.active = active;
        this.inSystemFrom = inSystemFrom;
        this.lastActivity = lastActivity;
    }
}
