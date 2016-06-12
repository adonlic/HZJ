package hr.fer.ppj.projekt.hzj.core.repositories.implementations;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.business.Statistics;
import hr.fer.ppj.projekt.hzj.core.repositories.IStatisticsRepository;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 21.5.2016..
 */
public class StatisticsRepository extends HZJRepository<Statistics>
        implements IStatisticsRepository {
    private List<Statistics> statistics;

    public StatisticsRepository(Context context) {
        super(context);

        this.statistics = new ArrayList<>();
    }

    @Override
    public void add(Statistics object) {
        this.statistics.add(object);
    }

    @Override
    public void addSome(List<Statistics> objects) {
        this.statistics.addAll(objects);
    }

    @Override
    public void remove(Statistics object) {
        this.statistics.remove(object);
    }

    @Override
    public void removeSome(List<Statistics> objects) {
        this.statistics.removeAll(objects);
    }

    @Override
    public Statistics get(int position) {
        return this.statistics.get(position);
    }

    @Override
    public List<Statistics> getAll() {
        return this.statistics;
    }

    @Override
    public void fetchAt(int position) {
        Call<List<Statistics>> call = HZJService.getService().getUserStatistics(position);
        call.enqueue(new Callback<List<Statistics>>() {
            @Override
            public void onResponse(Call<List<Statistics>> call,
                                   Response<List<Statistics>> response) {
                statistics = response.body();
            }

            @Override
            public void onFailure(Call<List<Statistics>> call, Throwable t) {

            }
        });
    }

    @Override
    public void update(int index) {
        /*
        Call<Void> call = HZJService.getService().updateUserStatistics(index, null);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
        */
    }
}
