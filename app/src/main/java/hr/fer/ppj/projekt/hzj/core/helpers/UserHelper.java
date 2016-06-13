package hr.fer.ppj.projekt.hzj.core.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.cache.UserCache;
import hr.fer.ppj.projekt.hzj.core.models.app.HZJApplication;
import hr.fer.ppj.projekt.hzj.core.models.business.Achievement;
import hr.fer.ppj.projekt.hzj.core.models.business.QuizResult;
import hr.fer.ppj.projekt.hzj.core.models.business.Statistics;
import hr.fer.ppj.projekt.hzj.core.models.business.User;
import hr.fer.ppj.projekt.hzj.core.models.business.Video;
import hr.fer.ppj.projekt.hzj.core.models.business.helper.UserCredentials;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.ILogin;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.IRegister;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 18.5.2016..
 */
public class UserHelper {
    private static Context context = HZJApplication.getContext();

    public static boolean isLogged() {
        SharedPreferences userPref = context.getSharedPreferences(
                context.getString(R.string.preference_user), Context.MODE_PRIVATE
        );
        // SharedPreferences.Editor editor = userPref.edit();

        // returns state of his status (logged in or needs to log in...)
        if (userPref.getBoolean("Aktivan", false)) {
            int userId = getUser();

            // now when we have user id, we use it for getting user from API...
            UserHelper.getLoggedUser(userId);

            return true;
        }

        return false;
    }

    private static int getUser() {
        SharedPreferences userPref = context.getSharedPreferences(
                context.getString(R.string.preference_user), Context.MODE_PRIVATE
        );

        return userPref.getInt("ID", 0);
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

    public static void getUserStats() {
        Call<List<Statistics>> call = HZJService.getService()
                .getUserStatistics(UserCache.getUser().getId());
        call.enqueue(new Callback<List<Statistics>>() {
            @Override
            public void onResponse(Call<List<Statistics>> call, Response<List<Statistics>> response) {
                UserCache.getUser().setStatisticsList(response.body());
            }

            @Override
            public void onFailure(Call<List<Statistics>> call, Throwable t) {

            }
        });
    }

    public static void getUserAchievements() {
        Call<List<Achievement>> call = HZJService.getService()
                .getUserAchievements(UserCache.getUser().getId());
        call.enqueue(new Callback<List<Achievement>>() {
            @Override
            public void onResponse(Call<List<Achievement>> call, Response<List<Achievement>> response) {
                UserCache.getUser().setAchievementList(response.body());
            }

            @Override
            public void onFailure(Call<List<Achievement>> call, Throwable t) {

            }
        });
    }

    public static void getUserQuizResults(final IObserveUser observeUser) {
        Call<List<QuizResult>> call = HZJService.getService()
                .getUserQuizResults(UserCache.getUser().getId());
        call.enqueue(new Callback<List<QuizResult>>() {
            @Override
            public void onResponse(Call<List<QuizResult>> call, Response<List<QuizResult>> response) {
                UserCache.getUser().setQuizResultList(response.body());
                observeUser.notifySpecificResult();
            }

            @Override
            public void onFailure(Call<List<QuizResult>> call, Throwable t) {

            }
        });
    }

    public static void fetchUserFavorites(IObserver observer) {
        Call<List<Video>> call = HZJService.getService()
                .getUserFavorites(UserCache.getUser().getId());
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                // UserCache.getUser().setFavoriteList(response.body());

            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });
    }

    public static void postUserStats() {
        Call<Boolean> call = HZJService.getService()
                .updateUserStatistics(UserCache.getUser().getId(),
                        UserCache.getUser().getStatisticsList());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public static void postUserAchievements() {
        Call<Boolean> call = HZJService.getService()
                .updateUserAchievements(UserCache.getUser().getId(),
                        UserCache.getUser().getAchievementList());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public static void postUserQuizResults(int quizId, int numberOfGood) {
        Call<Boolean> call = HZJService.getService()
                .updateUserQuizResult(UserCache.getUser().getId(),
                        quizId, numberOfGood);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }
}
