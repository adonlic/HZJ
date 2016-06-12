package hr.fer.ppj.projekt.hzj.core.cache;

import hr.fer.ppj.projekt.hzj.core.models.business.User;

/**
 * Created by ANTE on 12.6.2016..
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
}
