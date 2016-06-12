package hr.fer.ppj.projekt.hzj.core.models.app;

import android.app.Application;
import android.content.Context;

import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;

/**
 * Created by ANTE on 11.6.2016..
 */
public class HZJApplication extends Application {
    // private static HZJApplication ourInstance = new HZJApplication();
    private static Context context;
    private static HZJContext localCache;

    /*
    public static HZJApplication getInstance() {
        return ourInstance;
    }
    */

    public HZJApplication() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        localCache = new HZJContext(this);
        HZJService.setupAPI();
    }

    public static Context getContext() {
        return context;
    }
}
