package hr.fer.ppj.projekt.hzj.core.repositories;

/**
 * Created by ANTE on 21.5.2016..
 */
public interface IHZJContext {
    IAchievementRepository Achievements();                  // work with user's trophies
    IVideoRepository Favorites();                        // work with user's list of favorites
    IHardnessRepository Hardnesses();                       // work with list of quiz hardness
    IVideoRepository QuizHasVideos();                // work with quiz's videos
    IQuizRepository Quizzes();                              // work with list of quizzes
    IQuizResultRepository QuizResults();                    // working with user's quiz data
    ISectionRepository Sections();                          // work with list of sections
    IStatisticsRepository Statistics();                     // work with user's statistics
    IStatisticsTypeRepository StatisticTypes();             // work with statistic types
    ITrophyRepository Trophies();                           // work with list of trophies
    IUserRepository User();                                 // work with user's info data
    IVideoRepository Videos();                              // work with list of videos
    void saveChanges();
}
