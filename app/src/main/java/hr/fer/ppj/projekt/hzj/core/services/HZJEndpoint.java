package hr.fer.ppj.projekt.hzj.core.services;

import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.business.Achievement;
import hr.fer.ppj.projekt.hzj.core.models.business.Hardness;
import hr.fer.ppj.projekt.hzj.core.models.business.Quiz;
import hr.fer.ppj.projekt.hzj.core.models.business.QuizResult;
import hr.fer.ppj.projekt.hzj.core.models.business.Section;
import hr.fer.ppj.projekt.hzj.core.models.business.Statistics;
import hr.fer.ppj.projekt.hzj.core.models.business.StatisticsType;
import hr.fer.ppj.projekt.hzj.core.models.business.Trophy;
import hr.fer.ppj.projekt.hzj.core.models.business.User;
import hr.fer.ppj.projekt.hzj.core.models.business.Video;
import hr.fer.ppj.projekt.hzj.core.models.business.helper.UserCredentials;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ANTE on 18.5.2016..
 */
public interface HZJEndpoint {
    // VIDEO ENDPOINTS (tutorials)
    // get video/s
    @GET("video")
    Call<List<Video>> getAllVideos();                                                               // goes in VideoRepository (OPTIONAL)


    // USER(ACCOUNT) ENDPOINTS (users)
    // create user(account), get current user(account) info, deactivate current user(account)
    @POST("korisnik/novi")
    Call<Boolean> createNewUser(@Body User user);                                                      // goes online
    @GET("korisnik/Index/{id}")
    Call<User> getUserInfo(@Path("id") int userId);                                                 // goes in UserRepository
    // update user data
    @POST("korisnik/azuriraj/{id}")
    Call<Void> updateUserInfo(@Path("id") int userId);                                              // goes online
    // deaktivacija means logout...
    @POST("korisnik/deaktivacija/{id}")
    Call<Void> deactivateUser(@Path("id") int userId);                                              // goes online
    // login from GET to POST???
    @POST("korisnik/GetUser")  // NOT PROTECTED! ;)
    Call<User> login(@Body UserCredentials credentials);
    // Call<User> login(@Path("username") String username, @Path("password") String password);
    // all statistics is created for user and set to zero by default so we can push all
    // even when small change occurred...same thing for achievements...
    // update user's quiz progress and with it total progress (statistics) - statistics is cascade
    // adds or updates existing statistics
    @GET("korisnik/statistika/{id}")
    Call<List<Statistics>> getUserStatistics(@Path("id") int userId);                               // goes in StatisticsRepository
    @POST("korisnik/statistika/{id}")
    Call<Boolean> updateUserStatistics(@Path("id") int userId, @Body List<Statistics> userStats);                                        // goes online
    @GET("korisnik/postignuce/{id}")
    Call<List<Achievement>> getUserAchievements(@Path("id") int userId);                            // goes in AchievementRepository
    @POST("korisnik/postignuce/{id}")
    Call<Boolean> updateUserAchievements(@Path("id") int userId, @Body List<Achievement> achievements);// goes online
    @GET("korisnik/kvizovi/{id}")
    Call<List<QuizResult>> getUserQuizResults(@Path("id") int userId);                              // goes in QuizResultRepository
    @POST("korisnik/rezultat_kviza/{id}/{kviz_id}/{broj_savladanih}")
    Call<Boolean> updateUserQuizResult(@Path("id") int userId, @Path("kviz_id") int quizId,
                            @Path("broj_savladanih") int number);                                   // goes online
    // add or remove selected video to/from favorites by user's command
    @GET("korisnik/favoriti/{id}")
    Call<List<Video>> getUserFavorites(@Path("id") int userId);                                     // goes in FavoriteRepository
    @POST("korisnik/favorit/{id}/{video_id}")
    Call<Void> addFavoriteVideo(@Path("id") int userId, @Path("video_id") int videoId);             // goes online
    // if exists this favorite, with post we erase it, if doesn't...add it to user...
    @POST("korisnik/favorit/{id}/{video_id}")
    Call<Void> removeFavoriteVideo(@Path("id") int userId, @Path("video_id") int videoId);


    // SECTION ENDPOINTS (animals, politics...)
    // get sections, get current section's videos...
    @GET("skupina")
    Call<List<Section>> getAllSections();                                                           // goes in SectionRepository
    @GET("skupina/video/{skupina_id}")
    Call<List<Video>> getVideosFromSelectedSection(@Path("skupina_id") int sectionId);              // goes in VideoRepository

    // QUIZ ENDPOINTS (mixed set of videos ea.)
    // get quizzes, get current quiz's videos that user has to guess what they mean
    @GET("kviz")
    Call<List<Quiz>> getAllQuizzes();                                                               // goes in QuizRepository
    @GET("kviz/video/{id}")
    Call<List<Video>> getVideosFromSelectedQuiz(@Path("id") int quizId);                            // goes in QuizHasVideoRepository


    // ##################### OPTIONAL #####################
    @GET("trofeji")
    Call<List<Trophy>> getTrophies();                                                               // goes in TrophyRepository
    @GET("tipStatistike")
    Call<List<StatisticsType>> getStatisticsTypes();                                                // goes in StatisticsTypeRepository
    @GET("tezina")
    Call<List<Hardness>> getHardnesses();                                                           // goes in HardnessRepository


    // get all video names
    @GET("video/getNames")
    Call<List<Video>> getVideoNames();
}
