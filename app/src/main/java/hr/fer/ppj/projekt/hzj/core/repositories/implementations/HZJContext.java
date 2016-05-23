package hr.fer.ppj.projekt.hzj.core.repositories.implementations;

import android.content.Context;

import hr.fer.ppj.projekt.hzj.core.repositories.IAchievementRepository;
import hr.fer.ppj.projekt.hzj.core.repositories.IFavoriteRepository;
import hr.fer.ppj.projekt.hzj.core.repositories.IHZJContext;
import hr.fer.ppj.projekt.hzj.core.repositories.IHardnessRepository;
import hr.fer.ppj.projekt.hzj.core.repositories.IQuizHasVideoRepository;
import hr.fer.ppj.projekt.hzj.core.repositories.IQuizRepository;
import hr.fer.ppj.projekt.hzj.core.repositories.IQuizResultRepository;
import hr.fer.ppj.projekt.hzj.core.repositories.ISectionRepository;
import hr.fer.ppj.projekt.hzj.core.repositories.IStatisticsRepository;
import hr.fer.ppj.projekt.hzj.core.repositories.IStatisticsTypeRepository;
import hr.fer.ppj.projekt.hzj.core.repositories.ITrophyRepository;
import hr.fer.ppj.projekt.hzj.core.repositories.IUserRepository;
import hr.fer.ppj.projekt.hzj.core.repositories.IVideoRepository;

/**
 * Created by ANTE on 21.5.2016..
 */
public class HZJContext implements IHZJContext {
    // context, whatever it is in meaning of application, activity, fragment...
    private Context context;
    private AchievementRepository achievementRepository;
    private FavoriteRepository favoriteRepository;
    private HardnessRepository hardnessRepository;
    private QuizRepository quizRepository;
    private QuizHasVideoRepository quizHasVideoRepository;
    private QuizResultRepository quizResultRepository;
    private SectionRepository sectionRepository;
    private StatisticsRepository statisticsRepository;
    private StatisticsTypeRepository statisticsTypeRepository;
    private TrophyRepository trophyRepository;
    private UserRepository userRepository;
    private VideoRepository videoRepository;


    public HZJContext(Context context) {
        this.context = context;
        this.achievementRepository = new AchievementRepository(context);
        this.favoriteRepository = new FavoriteRepository(context);
        this.hardnessRepository = new HardnessRepository(context);
        this.quizRepository = new QuizRepository(context);
        this.quizHasVideoRepository = new QuizHasVideoRepository(context);
        this.quizResultRepository = new QuizResultRepository(context);
        this.sectionRepository = new SectionRepository(context);
        this.statisticsRepository = new StatisticsRepository(context);
        this.statisticsTypeRepository = new StatisticsTypeRepository(context);
        this.trophyRepository = new TrophyRepository(context);
        this.userRepository = new UserRepository(context);
        this.videoRepository = new VideoRepository(context);
    }

    @Override
    public IAchievementRepository Achievements() {
        return this.achievementRepository;
    }

    @Override
    public IVideoRepository Favorites() {
        return this.favoriteRepository;
    }

    @Override
    public IHardnessRepository Hardnesses() {
        return this.hardnessRepository;
    }

    @Override
    public IVideoRepository QuizHasVideos() {
        return this.quizHasVideoRepository;
    }

    @Override
    public IQuizRepository Quizzes() {
        return this.quizRepository;
    }

    @Override
    public IQuizResultRepository QuizResults() {
        return this.quizResultRepository;
    }

    @Override
    public ISectionRepository Sections() {
        return this.sectionRepository;
    }

    @Override
    public IStatisticsRepository Statistics() {
        return this.statisticsRepository;
    }

    @Override
    public IStatisticsTypeRepository StatisticTypes() {
        return this.statisticsTypeRepository;
    }

    @Override
    public ITrophyRepository Trophies() {
        return this.trophyRepository;
    }

    @Override
    public IUserRepository User() {
        return this.userRepository;
    }

    @Override
    public IVideoRepository Videos() {
        return this.videoRepository;
    }

    @Override
    public void saveChanges() {

    }
}
