package hr.fer.ppj.projekt.hzj.core.cache;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.business.User;
import hr.fer.ppj.projekt.hzj.core.models.business.Video;

/**
 * Created by ANTE on 20.5.2016..
 */
public class UserCache {
    private static User user;

    public static User getUser() {
        return user;
    }

    /*
    public static void setUser(User user) {
        UserCache.user = user;
    }
    */

    // sets user that's currently using the app
    public static void use(User user) {
        UserCache.user = user;
    }

    // because we saved Videos inside of requested user's favorites in this cache class, we now
    // must extract it in the same way if we want to use it...
    public static List<Video> getUserFavorites() {
        List<Video> userVideos = new ArrayList<>();

        for (int i = 0; i < UserCache.user.getFavoriteList().size(); i++) {
            userVideos.add(
                    UserCache.user
                            .getFavoriteList()
                            .get(i).getVideo());
        }

        return userVideos;
    }
}
