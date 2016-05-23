package hr.fer.ppj.projekt.hzj.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ANTE on 20.5.2016..
 */

// PRESJECNA TABLICA (ima surogat kljuc 'ID')
public class Statistics {
    // private fields
    @SerializedName("ID")
    @Expose
    private int id;
    @SerializedName("KorisnikID")
    @Expose
    private int userId;
    @SerializedName("TipStatistikeID")
    @Expose
    private int statisticsTypeId;
    @SerializedName("Vrijednost")
    @Expose
    private double value;

    // references to other tables
    private User user;
    private StatisticsType statisticsType;

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

    public int getStatisticsTypeId() {
        return statisticsTypeId;
    }

    public void setStatisticsTypeId(int statisticsTypeId) {
        this.statisticsTypeId = statisticsTypeId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    // getters and setters for references
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StatisticsType getStatisticsType() {
        return statisticsType;
    }

    public void setStatisticsType(StatisticsType statisticsType) {
        this.statisticsType = statisticsType;
    }

    // constructors
    public Statistics() {

    }

    public Statistics(int id, int userId, int statisticsTypeId, double value) {
        this.id = id;
        this.userId = userId;
        this.statisticsTypeId = statisticsTypeId;
        this.value = value;
    }
}
