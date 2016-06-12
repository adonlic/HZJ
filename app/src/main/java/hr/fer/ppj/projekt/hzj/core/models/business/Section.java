package hr.fer.ppj.projekt.hzj.core.models.business;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ANTE on 20.5.2016..
 */
public class Section {
    // private fields
    @SerializedName("ID")
    @Expose
    private int id;
    @SerializedName("Naziv")
    @Expose
    private String name;
    @SerializedName("Opis")
    @Expose
    private String description;


    private String backgroundImageURL;


    // references to other tables
    private List<Video> videoList;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // getters and setters for references
    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public String getBackgroundImageURL() {
        return backgroundImageURL;
    }

    public void setBackgroundImageURL(String backgroundImageURL) {
        this.backgroundImageURL = backgroundImageURL;
    }


    // constructors
    public Section() {

    }

    public Section(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /*
    public void downloadImage() {
        Log.i("Section --> ", "downloading");
        String[] fragments = this.name.split(" ");
        String n = "";

        for (int i = 0; i < fragments.length - 1; i++) {
            n += fragments[i] + "_";
        }
        n += fragments[fragments.length - 1];
        n = n.toLowerCase();

        new ImageDownloader().execute(n);

        Picasso.with()
    }*/

    /*
    public class ImageDownloader extends AsyncTask<String, Void, Drawable> {
        @Override
        protected Drawable doInBackground(String... imageNames) {
            String imageURL = imageNames[0];
            imageURL= "http://hzjservice.azurewebsites.net/Image?img=" + imageURL;
            Bitmap bitmap = null;
            Drawable d = null;

            try {
                // Download Image from URL
                // InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                // bitmap = BitmapFactory.decodeStream(input);
                InputStream is = (InputStream) new URL(imageURL).getContent();
                d = Drawable.createFromStream(is, name);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return d;
        }

        @Override
        protected void onPostExecute(Drawable result) {
            // Set the bitmap into ImageView
            setBackgroundImage(result);
        }
    }
    */
}
