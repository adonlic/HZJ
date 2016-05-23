package hr.fer.ppj.projekt.hzj.core.repositories.implementations;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.QuizResult;
import hr.fer.ppj.projekt.hzj.core.repositories.IQuizResultRepository;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 21.5.2016..
 */
public class QuizResultRepository extends HZJRepository<QuizResult> implements IQuizResultRepository {
    private List<QuizResult> quizResults;

    public QuizResultRepository(Context context) {
        super(context);

        this.quizResults = new ArrayList<>();
    }

    @Override
    public void add(QuizResult object) {
        this.quizResults.add(object);
    }

    @Override
    public void addSome(List<QuizResult> objects) {
        this.quizResults.addAll(objects);
    }

    @Override
    public void remove(QuizResult object) {
        this.quizResults.remove(object);
    }

    @Override
    public void removeSome(List<QuizResult> objects) {
        this.quizResults.removeAll(objects);
    }

    @Override
    public QuizResult get(int position) {
        return this.quizResults.get(position);
    }

    @Override
    public List<QuizResult> getAll() {
        return this.quizResults;
    }

    @Override
    public void fetchAt(int index) {
        // position here is user's ID
        Call<List<QuizResult>> call = HZJService.getService().getUserQuizResults(index);
        call.enqueue(new Callback<List<QuizResult>>() {
            @Override
            public void onResponse(Call<List<QuizResult>> call, Response<List<QuizResult>> response) {
                quizResults = response.body();
            }

            @Override
            public void onFailure(Call<List<QuizResult>> call, Throwable t) {

            }
        });
    }

    @Override
    public void update(int index) {

    }

    @Override
    public void updateAll() {

    }
}
