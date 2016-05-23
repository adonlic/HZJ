package hr.fer.ppj.projekt.hzj.core.services;

import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.Achievement;
import hr.fer.ppj.projekt.hzj.core.models.Favorite;
import hr.fer.ppj.projekt.hzj.core.models.Hardness;
import hr.fer.ppj.projekt.hzj.core.models.Quiz;
import hr.fer.ppj.projekt.hzj.core.models.QuizResult;
import hr.fer.ppj.projekt.hzj.core.models.Section;
import hr.fer.ppj.projekt.hzj.core.models.Statistics;
import hr.fer.ppj.projekt.hzj.core.models.StatisticsType;
import hr.fer.ppj.projekt.hzj.core.models.Trophy;
import hr.fer.ppj.projekt.hzj.core.models.User;
import hr.fer.ppj.projekt.hzj.core.models.Video;
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
    Call<Void> createNewUser(@Body User user);                                                      // goes online
    @GET("korisnik/{id}")
    Call<User> getUserInfo(@Path("id") int userId);                                                 // goes in UserRepository
    @POST("korisnik/{id}")
    Call<Void> updateUserInfo(@Path("id") int userId);                                              // goes online
    @POST("korisnik/deaktivacija/{id}")
    Call<Void> deactivateUser(@Path("id") int userId);                                              // goes online
    // all statistics is created for user and set to zero by default so we can push all
    // even when small change occurred...same thing for achievements...
    // update user's quiz progress and with it total progress (statistics) - statistics is cascade
    @GET("korisnik/statistika/{id}")
    Call<List<Statistics>> getUserStatistics(@Path("id") int userId);                               // goes in StatisticsRepository
    @POST("korisnik/statistika/{id}")
    Call<Void> updateUserStatistics(@Path("id") int userId);                                        // goes online
    @GET("korisnik/postignuce/{id}")
    Call<List<Achievement>> getUserAchievements(@Path("id") int userId);                            // goes in AchievementRepository
    @POST("korisnik/postignuce/{id}")
    Call<Void> updateUserAchievements(@Path("id") int userId, @Body List<Achievement> achievements);// goes online
    @GET("korisnik/kvizovi/{id}")
    Call<List<QuizResult>> getUserQuizResults(@Path("id") int userId);                              // goes in QuizResultRepository
    @POST("korisnik/rezultat_kviza/{id}/{kviz_id}/{broj_savladanih}")
    Call<Void> updateUserQuizResult(@Path("id") int userId, @Path("kviz_id") int quizId,
                            @Path("broj_savladanih") int number);                                   // goes online
    // add or remove selected video to/from favorites by user's command
    @GET("korisnik/favoriti/{id}")
    Call<List<Video>> getUserFavorites(@Path("id") int userId);                                     // goes in FavoriteRepository
    @POST("korisnik/favorit/{id}/{video_id}")
    Call<Void> addFavoriteVideo(@Path("id") int userId, @Path("video_id") int videoId);             // goes online
    @DELETE("korisnik/favorit/{id}/{video_id}")
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
    @GET("statistike")
    Call<List<StatisticsType>> getStatisticsTypes();                                                // goes in StatisticsTypeRepository
    @GET("tezina")
    Call<List<Hardness>> getHardnesses();                                                           // goes in HardnessRepository

}
