package hr.fer.ppj.projekt.hzj.core.helpers;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.cache.QuizzesCache;
import hr.fer.ppj.projekt.hzj.core.models.app.HZJApplication;
import hr.fer.ppj.projekt.hzj.core.models.business.Quiz;
import hr.fer.ppj.projekt.hzj.core.models.business.QuizHasVideo;
import hr.fer.ppj.projekt.hzj.core.models.business.Video;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 20.5.2016..
 */
public class QuizHelper {
    private static Context context = HZJApplication.getContext();

    public static void fetchQuizzes(final IObserver observer) {
        Call<List<Quiz>> call = HZJService.getService().getAllQuizzes();
        call.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, Response<List<Quiz>> response) {
                if (response.body() != null) {
                    QuizzesCache.setQuizzes(response.body());
                    observer.notifyDownloaded(true);
                }
                else
                    observer.notifyDownloaded(false);
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {
                observer.notifyDownloaded(false);
            }
        });
    }

    public static void fetchQuizVideos(final IObserver observer, final int quizId) {
        Call<List<Video>> call = HZJService.getService()
                .getVideosFromSelectedQuiz(quizId);
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                List<QuizHasVideo> quizHasVideos = new ArrayList<QuizHasVideo>();

                if (response.body() != null) {
                    for (Video v : response.body()) {
                        // we didn't have QuizHasVideo stuff so we'll from response make it...
                        QuizHasVideo quizHasVideo = new QuizHasVideo();
                        // quizHasVideo.setQuizId(quizId);
                        // quizHasVideo.setQuiz(QuizzesCache.getQuizzes().get(quizId));
                        quizHasVideo.setVideoId(v.getId());
                        quizHasVideo.setVideo(v);
                        quizHasVideos.add(quizHasVideo);
                    }

                    QuizzesCache.getQuizzes().get(quizId).setQuizHasVideoList(quizHasVideos);
                    observer.notifyDownloaded(true);
                }
                else
                    observer.notifyDownloaded(false);
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                observer.notifyDownloaded(false);
            }
        });
    }
}
