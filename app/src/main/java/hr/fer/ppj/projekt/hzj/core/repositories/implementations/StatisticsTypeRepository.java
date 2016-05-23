package hr.fer.ppj.projekt.hzj.core.repositories.implementations;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.StatisticsType;
import hr.fer.ppj.projekt.hzj.core.repositories.IStatisticsTypeRepository;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 21.5.2016..
 */
public class StatisticsTypeRepository extends HZJRepository<StatisticsType>
        implements IStatisticsTypeRepository {
    private List<StatisticsType> statisticsTypes;

    public StatisticsTypeRepository(Context context) {
        super(context);

        this.statisticsTypes = new ArrayList<>();
    }

    @Override
    public void add(StatisticsType object) {
        this.statisticsTypes.add(object);
    }

    @Override
    public void addSome(List<StatisticsType> objects) {
        this.statisticsTypes.addAll(objects);
    }

    @Override
    public void remove(StatisticsType object) {
        this.statisticsTypes.remove(object);
    }

    @Override
    public void removeSome(List<StatisticsType> objects) {
        this.statisticsTypes.removeAll(objects);
    }

    @Override
    public StatisticsType get(int position) {
        return this.statisticsTypes.get(position);
    }

    @Override
    public List<StatisticsType> getAll() {
        return this.statisticsTypes;
    }

    @Override
    public void fetchAll() {
        Call<List<StatisticsType>> call = HZJService.getService().getStatisticsTypes();
        call.enqueue(new Callback<List<StatisticsType>>() {
            @Override
            public void onResponse(Call<List<StatisticsType>> call,
                                   Response<List<StatisticsType>> response) {
                statisticsTypes = response.body();
            }

            @Override
            public void onFailure(Call<List<StatisticsType>> call, Throwable t) {

            }
        });
    }
}
