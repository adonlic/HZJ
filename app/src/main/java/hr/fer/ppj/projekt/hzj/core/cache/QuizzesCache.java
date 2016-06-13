package hr.fer.ppj.projekt.hzj.core.cache;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.business.Quiz;
import hr.fer.ppj.projekt.hzj.core.models.business.Video;

/**
 * Created by ANTE on 12.6.2016..
 */
public class QuizzesCache {
    private static List<Quiz> quizzes;

    public static List<Quiz> getQuizzes() {
        return quizzes;
    }

    public static void setQuizzes(List<Quiz> quizzes) {
        QuizzesCache.quizzes = quizzes;
    }

    // because we saved Videos inside of requested quiz in this cache class, we now
    // must extract it in the same way if we want to use it...
    public static List<Video> getQuizVideos(int quizId) {
        List<Video> quizVideos = new ArrayList<>();

        for (int i = 0; i < QuizzesCache.quizzes.get(quizId).getQuizHasVideoList().size(); i++) {
            quizVideos.add(
                    QuizzesCache.quizzes.get(quizId)
                            .getQuizHasVideoList()
                            .get(i).getVideo());
        }

        return quizVideos;
    }
}
