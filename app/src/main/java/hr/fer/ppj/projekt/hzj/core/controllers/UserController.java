package hr.fer.ppj.projekt.hzj.core.controllers;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.cache.UserCache;
import hr.fer.ppj.projekt.hzj.core.models.app.HZJApplication;
import hr.fer.ppj.projekt.hzj.core.models.business.Achievement;
import hr.fer.ppj.projekt.hzj.core.models.business.Statistics;
import hr.fer.ppj.projekt.hzj.core.models.business.User;
import hr.fer.ppj.projekt.hzj.core.models.business.helper.UserCredentials;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.ILogin;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.IRegister;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 11.6.2016..
 */
public class UserController {
    private Context context = HZJApplication.getContext();

    public boolean isLogged() {
        SharedPreferences userPref = context.getSharedPreferences(
                context.getString(R.string.preference_user), Context.MODE_PRIVATE
        );
        // SharedPreferences.Editor editor = userPref.edit();

        // returns state of his status (logged in or needs to log in...)
        if (userPref.getBoolean("Aktivan", false)) {
            int userId = getUser();

            // now when we have user id, we use it for getting user from API...
            getLoggedUser(userId);

            return true;
        }

        return false;
    }

    private int getUser() {
        SharedPreferences userPref = context.getSharedPreferences(
                context.getString(R.string.preference_user), Context.MODE_PRIVATE
        );

        return userPref.getInt("Id", 0);
    }

    public static void tryToLogin(final ILogin loginFragment,
                                  String username, String password) {
        UserCredentials credentials = new UserCredentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        // NOT PROTECTED...must use Base64 encoding or something!
        Call<User> call = HZJService.getService().login(credentials);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // if response.body() returned not null, we save user in cache
                // so, if it is null in successful response, wrong credentials...
                if (response.body() != null)
                    UserCache.use(response.body());

                loginFragment.setLoginResult(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginFragment.setLoginResult(null);
            }
        });
    }

    public static void tryToRegisterNew(final IRegister registerFragment,
                                        String username, String password,
                                        String name, String surname) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);

        // NOT PROTECTED...must use Base64 encoding or something!
        Call<Boolean> call = HZJService.getService().createNewUser(user);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                // if response.body() returned not null, we save user in cache
                if (response.body() == true)
                    UserCache.use(user);

                // if username exists, returns false, else upon successful returns true
                registerFragment.setRegistrationResult(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                registerFragment.setRegistrationResult(false);
            }
        });
    }

    private static void getLoggedUser(int id) {
        Call<User> call = HZJService.getService().getUserInfo(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // if we can get him...
                if (response.body() != null) {
                    UserCache.use(response.body());

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //loginFragment.setLoginResult(null);
            }
        });
    }

    public static void getUserStats(int id) {
        Call<List<Statistics>> call = HZJService.getService().getUserStatistics(id);
        call.enqueue(new Callback<List<Statistics>>() {
            @Override
            public void onResponse(Call<List<Statistics>> call, Response<List<Statistics>> response) {

            }

            @Override
            public void onFailure(Call<List<Statistics>> call, Throwable t) {

            }
        });
    }

    public static void postUserStats(int id) {


        Call<Void> call = HZJService.getService().updateUserStatistics(id, null);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public static void getUserAchievements(int id) {
        Call<List<Achievement>> call = HZJService.getService().getUserAchievements(id);
        call.enqueue(new Callback<List<Achievement>>() {
            @Override
            public void onResponse(Call<List<Achievement>> call, Response<List<Achievement>> response) {

            }

            @Override
            public void onFailure(Call<List<Achievement>> call, Throwable t) {

            }
        });
    }

    public static void getUserQuizResults() {

    }

    public static void getUserFavorites() {

    }
}
