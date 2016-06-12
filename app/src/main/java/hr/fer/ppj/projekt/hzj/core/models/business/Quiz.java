package hr.fer.ppj.projekt.hzj.core.models.business;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ANTE on 19.5.2016..
 */
public class Quiz {
    // private fields
    @SerializedName("ID")
    @Expose
    private int id;
    @SerializedName("Naslov")
    @Expose
    private String title;
    @SerializedName("Opis")
    @Expose
    private String description;
    @SerializedName("TezinaID")
    @Expose
    private int hardenessId;


    private String backgroundImageURL;


    // references to other tables
    private List<QuizHasVideo>  quizHasVideoList;
    private Hardness hardness;
    private List<QuizResult> quizResultList;

    // getters and setters;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHardenessId() {
        return hardenessId;
    }

    public void setHardenessId(int hardenessId) {
        this.hardenessId = hardenessId;
    }

    // getters and setters for references
    public List<QuizHasVideo> getQuizHasVideoList() {
        return quizHasVideoList;
    }

    public void setQuizHasVideoList(List<QuizHasVideo> quizHasVideoList) {
        this.quizHasVideoList = quizHasVideoList;
    }

    public Hardness getHardness() {
        return hardness;
    }

    public void setHardness(Hardness hardness) {
        this.hardness = hardness;
    }

    public List<QuizResult> getQuizResultList() {
        return quizResultList;
    }

    public void setQuizResultList(List<QuizResult> quizResultList) {
        this.quizResultList = quizResultList;
    }

    public String getBackgroundImageURL() {
        return backgroundImageURL;
    }

    public void setBackgroundImageURL(String backgroundImageURL) {
        this.backgroundImageURL = backgroundImageURL;
    }

    // constructors
    public Quiz() {

    }

    public Quiz(int id, String title, String description, int hardenessId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.hardenessId = hardenessId;
    }
}
