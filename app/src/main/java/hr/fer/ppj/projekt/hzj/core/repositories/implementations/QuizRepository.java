package hr.fer.ppj.projekt.hzj.core.repositories.implementations;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.Quiz;
import hr.fer.ppj.projekt.hzj.core.repositories.IQuizRepository;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 21.5.2016..
 */
public class QuizRepository extends HZJRepository<Quiz> implements IQuizRepository {
    private List<Quiz> quizzes;

    public QuizRepository(Context context) {
        super(context);

        this.quizzes = new ArrayList<>();
    }

    @Override
    public void add(Quiz object) {
        this.quizzes.add(object);
    }

    @Override
    public void addSome(List<Quiz> objects) {
        this.quizzes.addAll(objects);
    }

    @Override
    public void remove(Quiz object) {
        this.quizzes.remove(object);
    }

    @Override
    public void removeSome(List<Quiz> objects) {
        this.quizzes.removeAll(objects);
    }

    @Override
    public Quiz get(int position) {
        return this.quizzes.get(position);
    }

    @Override
    public List<Quiz> getAll() {
        return this.quizzes;
    }

    @Override
    public void fetchAll() {
        Call<List<Quiz>> call = HZJService.getService().getAllQuizzes();
        call.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, Response<List<Quiz>> response) {
                quizzes = response.body();
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {

            }
        });
    }

    @Override
    public List<Quiz> getByHardness(int dbID) {
        List<Quiz> byHardness = new ArrayList<>();

        for (Quiz quiz : quizzes) {
            if (quiz.getHardenessId() == dbID)
                byHardness.add(quiz);
        }

        return byHardness;
    }
}
