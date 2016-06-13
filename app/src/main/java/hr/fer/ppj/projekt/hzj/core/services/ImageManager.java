package hr.fer.ppj.projekt.hzj.core.services;

import hr.fer.ppj.projekt.hzj.core.models.business.Quiz;
import hr.fer.ppj.projekt.hzj.core.models.business.Section;

/**
 * Created by ANTE on 23.5.2016..
 */
public class ImageManager {
    private static String onlineSectionImageRepository = "http://hzjservice.azurewebsites.net/Image?img=";
    private static String onlineQuizImageRepository = "http://hzjservice.azurewebsites.net/KvizImage?img=";

    public static void setSectionImageURL(Section section) {
        String title = section.getName();
        String[] fragments = title.split(" ");
        String url = "";

        for (int i = 0; i < fragments.length; i++) {
            url += fragments[i] + "_";
        }
        url = url.substring(0, url.length() - 1);
        url = url.replace("š", "%C5%A1");
        url = url.replace("Š", "%C5%A1");
        url = url.replace("ć", "%C4%87");
        url = url.replace("Ć", "%C4%87");
        url = url.replace("ž", "%C5%BE");
        url = url.replace("Ž", "%C5%BE");
        url = url.replace("đ", "%C4%91");
        url = url.replace("Đ", "%C4%91");

        section.setBackgroundImageURL(ImageManager.onlineSectionImageRepository + url);
        // Log.i("IMAGE MANAGER", section.getBackgroundImageURL());
    }

    public static void setQuizImageURL(Quiz quiz) {
        int id = quiz.getId();

        quiz.setBackgroundImageURL(ImageManager.onlineQuizImageRepository + id);
        // Log.i("IMAGE MANAGER", section.getBackgroundImageURL());
    }

    /*
    public static void setVideoImage(Video video, Context context) {
        int id = video.getSectionId();

        if (id == 1 || id == 2 || id == 8 || id == 9 || id == 19)
            video.setBackgroundImage(context.getResources().getDrawable(R.drawable.thumb1));
        else if (id == 3 || id == 4 || id == 6 || id == 7 || id == 10 || id == 11)
            video.setBackgroundImage(context.getResources().getDrawable(R.drawable.thumb2));
        else if (id == 5 || id == 13 || id == 15 || id == 17 || id == 20)
            video.setBackgroundImage(context.getResources().getDrawable(R.drawable.thumb3));
        else if (id == 12 || id == 14 || id == 16 || id == 18)
            video.setBackgroundImage(context.getResources().getDrawable(R.drawable.thumb4));
        // Log.i("IMAGE MANAGER", section.getBackgroundImageURL());
    }
    */
}
