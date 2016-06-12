package hr.fer.ppj.projekt.hzj.core.models.business;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ANTE on 21.5.2016..
 */
public class StatisticsType {
    // private fields
    @SerializedName("ID")
    @Expose
    private int id;
    @SerializedName("Naziv")
    @Expose
    private String name;
    @SerializedName("MaxVrijednost")
    @Expose
    private double maxValue;

    // references to other tables
    private List<Statistics> statisticsList;

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

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    // getters and setters for references
    public List<Statistics> getStatisticsList() {
        return statisticsList;
    }

    public void setStatisticsList(List<Statistics> statisticsList) {
        this.statisticsList = statisticsList;
    }

    // constructors
    public StatisticsType() {

    }

    public StatisticsType(int id, String name, double maxValue) {
        this.id = id;
        this.name = name;
        this.maxValue = maxValue;
    }
}
