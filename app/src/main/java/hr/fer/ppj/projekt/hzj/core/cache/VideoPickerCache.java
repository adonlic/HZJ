package hr.fer.ppj.projekt.hzj.core.cache;

import java.util.List;

/**
 * Created by ANTE on 13.6.2016..
 */
public class VideoPickerCache {
    private static List<String> pickers;

    public static List<String> getPickers() {
        return pickers;
    }

    public static void setPickers(List<String> pickers) {
        VideoPickerCache.pickers = pickers;
    }
}
