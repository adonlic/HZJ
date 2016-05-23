package hr.fer.ppj.projekt.hzj.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ANTE on 20.5.2016..
 */

// PRESJECNA TABLICA (ima surogat kljuc 'ID')
public class Achievement {
    // private fields
    @SerializedName("ID")
    @Expose
    private int id;
    @SerializedName("KorisnikID")
    @Expose
    private int userId;
    @SerializedName("TrofejID")
    @Expose
    private int trophyId;

    // references to other tables
    private User user;
    private Trophy trophy;

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

    public int getTrophyId() {
        return trophyId;
    }

    public void setTrophyId(int trophyId) {
        this.trophyId = trophyId;
    }

    // getters and setters for references
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trophy getTrophy() {
        return trophy;
    }

    public void setTrophy(Trophy trophy) {
        this.trophy = trophy;
    }

    // constructors
    public Achievement() {

    }

    public Achievement(int id, int userId, int trophyId) {
        this.id = id;
        this.userId = userId;
        this.trophyId = trophyId;
    }
}
