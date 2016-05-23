package hr.fer.ppj.projekt.hzj.core.services;

import android.util.Log;

import hr.fer.ppj.projekt.hzj.core.models.Section;

/**
 * Created by ANTE on 23.5.2016..
 */
public class ImageManager {
    private static String onlineImageRepository = "http://hzjservice.azurewebsites.net/Image?img=";

    public static void setImageURL(Section section) {
        String title = section.getName();
        String[] fragments = title.split(" ");
        String url = "";

        for (int i = 0; i < fragments.length; i++) {
            url += fragments[i] + "_";
        }
        url = url.substring(0, url.length() - 1);

        section.setBackgroundImageURL(ImageManager.onlineImageRepository + url);
        // Log.i("IMAGE MANAGER", section.getBackgroundImageURL());
    }
}
