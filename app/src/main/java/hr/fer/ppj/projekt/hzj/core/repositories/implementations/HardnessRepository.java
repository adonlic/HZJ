package hr.fer.ppj.projekt.hzj.core.repositories.implementations;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.Hardness;
import hr.fer.ppj.projekt.hzj.core.repositories.IHardnessRepository;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 21.5.2016..
 */
public class HardnessRepository extends HZJRepository<Hardness> implements IHardnessRepository {
    private List<Hardness> hardnesses;

    public HardnessRepository(Context context) {
        super(context);

        this.hardnesses = new ArrayList<>();
    }

    @Override
    public void add(Hardness object) {
        this.hardnesses.add(object);
    }

    @Override
    public void addSome(List<Hardness> objects) {
        this.hardnesses.addAll(objects);
    }

    @Override
    public void remove(Hardness object) {
        this.hardnesses.remove(object);
    }

    @Override
    public void removeSome(List<Hardness> objects) {
        this.hardnesses.removeAll(objects);
    }

    @Override
    public Hardness get(int position) {
        return this.hardnesses.get(position);
    }

    @Override
    public List<Hardness> getAll() {
        return this.hardnesses;
    }

    @Override
    public void fetchAll() {
        Call<List<Hardness>> call = HZJService.getService().getHardnesses();
        call.enqueue(new Callback<List<Hardness>>() {
            @Override
            public void onResponse(Call<List<Hardness>> call,
                                   Response<List<Hardness>> response) {
                hardnesses = response.body();
            }

            @Override
            public void onFailure(Call<List<Hardness>> call, Throwable t) {

            }
        });
    }

    @Override
    public void update(int index) {

    }
}
