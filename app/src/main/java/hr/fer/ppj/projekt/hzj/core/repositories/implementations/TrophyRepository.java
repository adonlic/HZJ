package hr.fer.ppj.projekt.hzj.core.repositories.implementations;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.Trophy;
import hr.fer.ppj.projekt.hzj.core.repositories.ITrophyRepository;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 21.5.2016..
 */
public class TrophyRepository extends HZJRepository<Trophy> implements ITrophyRepository {
    private List<Trophy> trophies;

    public TrophyRepository(Context context) {
        super(context);

        this.trophies = new ArrayList<>();
    }

    @Override
    public void add(Trophy object) {
        this.trophies.add(object);
    }

    @Override
    public void addSome(List<Trophy> objects) {
        this.trophies.addAll(objects);
    }

    @Override
    public void remove(Trophy object) {
        this.trophies.remove(object);
    }

    @Override
    public void removeSome(List<Trophy> objects) {
        this.trophies.removeAll(objects);
    }

    @Override
    public Trophy get(int position) {
        return this.trophies.get(position);
    }

    @Override
    public List<Trophy> getAll() {
        return this.trophies;
    }

    @Override
    public void fetchAll() {
        Call<List<Trophy>> call = HZJService.getService().getTrophies();
        call.enqueue(new Callback<List<Trophy>>() {
            @Override
            public void onResponse(Call<List<Trophy>> call, Response<List<Trophy>> response) {
                trophies = response.body();
            }

            @Override
            public void onFailure(Call<List<Trophy>> call, Throwable t) {

            }
        });
    }
}
