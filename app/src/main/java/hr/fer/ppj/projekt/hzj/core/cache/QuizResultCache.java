package hr.fer.ppj.projekt.hzj.core.cache;

import hr.fer.ppj.projekt.hzj.core.models.business.QuizResult;

/**
 * Created by ANTE on 13.6.2016..
 */
public class QuizResultCache {
    private static QuizResult quizResult;

    public static QuizResult getQuizResult() {
        return quizResult;
    }

    /*
    public static void setQuizResult(QuizResult quizResult) {
        QuizResultCache.quizResult = quizResult;
    }
    */

    public static void use(int quizId) {
        quizResult = new QuizResult();
        quizResult.setUserId(UserCache.getUser().getId());
        quizResult.setQuizId(quizId);
        // quizResult.setUser(UserCache.getUser());
        // quizResult.setQuiz(QuizzesCache.getQuizzes().get());
        quizResult.setNumberOfSuccessfulAnswers(0);
    }

    public static void raiseOne() {
        quizResult.setNumberOfSuccessfulAnswers(quizResult.getNumberOfSuccessfulAnswers() + 1);
    }
}
