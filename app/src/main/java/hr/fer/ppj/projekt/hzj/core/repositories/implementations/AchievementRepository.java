package hr.fer.ppj.projekt.hzj.core.repositories.implementations;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.business.Achievement;
import hr.fer.ppj.projekt.hzj.core.repositories.IAchievementRepository;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 21.5.2016..
 */
public class AchievementRepository extends HZJRepository<Achievement>
        implements IAchievementRepository {
    private List<Achievement> achievements;

    public AchievementRepository(Context context) {
        super(context);

        this.achievements = new ArrayList<>();
    }

    @Override
    public void add(Achievement object) {
        this.achievements.add(object);
    }

    @Override
    public void addSome(List<Achievement> objects) {
        this.achievements.addAll(objects);
    }

    @Override
    public void remove(Achievement object) {
        this.achievements.remove(object);
    }

    @Override
    public void removeSome(List<Achievement> objects) {
        this.achievements.removeAll(objects);
    }

    @Override
    public Achievement get(int position) {
        return this.achievements.get(position);
    }

    @Override
    public List<Achievement> getAll() {
        return achievements;
    }

    @Override
    public void fetchAt(int index) {
        // position here is user's ID
        Call<List<Achievement>> call = HZJService.getService().getUserAchievements(index);
        call.enqueue(new Callback<List<Achievement>>() {
            @Override
            public void onResponse(Call<List<Achievement>> call,
                                   Response<List<Achievement>> response) {
                achievements = response.body();
            }

            @Override
            public void onFailure(Call<List<Achievement>> call, Throwable t) {

            }
        });
    }

    @Override
    public void update(int index) {
        // position here is user's ID
        Call<Void> call = HZJService.getService().updateUserAchievements(index, achievements);      // <------
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
