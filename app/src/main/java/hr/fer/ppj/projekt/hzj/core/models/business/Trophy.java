package hr.fer.ppj.projekt.hzj.core.models.business;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ANTE on 21.5.2016..
 */
public class Trophy {
    // private fields
    @SerializedName("ID")
    @Expose
    private int id;
    @SerializedName("Naziv")
    @Expose
    private String name;

    // references to other tables
    private List<Achievement> achievementList;

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

    // getters and setters for references
    public List<Achievement> getAchievementList() {
        return achievementList;
    }

    public void setAchievementList(List<Achievement> achievementList) {
        this.achievementList = achievementList;
    }

    // constructors
    public Trophy() {

    }

    public Trophy(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
