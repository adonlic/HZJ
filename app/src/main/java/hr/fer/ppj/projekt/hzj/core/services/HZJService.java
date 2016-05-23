package hr.fer.ppj.projekt.hzj.core.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ANTE on 20.5.2016..
 */
public class HZJService {
    private static HZJService ourInstance = new HZJService();

    private static final String BASE_URL = "http://hzjservice.azurewebsites.net/";
    private static Retrofit retrofit;
    private static HZJEndpoint service;

    public static HZJService getInstance() {
        return ourInstance;
    }

    // getter for HZJEndpoint so we can access it from any location within application
    public static HZJEndpoint getService() {
        return service;
    }

    private HZJService() {

    }

    public static void setupAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(HZJEndpoint.class);
    }
}
