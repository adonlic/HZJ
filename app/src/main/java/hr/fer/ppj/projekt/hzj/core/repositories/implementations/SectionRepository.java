package hr.fer.ppj.projekt.hzj.core.repositories.implementations;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.business.Section;
import hr.fer.ppj.projekt.hzj.core.repositories.ISectionRepository;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 21.5.2016..
 */
public class SectionRepository extends HZJRepository<Section> implements ISectionRepository {
    private List<Section> sections;

    public SectionRepository(Context context) {
        super(context);

        this.sections = new ArrayList<>();
    }

    @Override
    public void add(Section object) {
        this.sections.add(object);
    }

    @Override
    public void addSome(List<Section> objects) {
        this.sections.addAll(objects);
    }

    @Override
    public void remove(Section object) {
        this.sections.remove(object);
    }

    @Override
    public void removeSome(List<Section> objects) {
        this.sections.removeAll(objects);
    }

    @Override
    public Section get(int position) {
        return this.sections.get(position);
    }

    @Override
    public List<Section> getAll() {
        return this.sections;
    }

    @Override
    public void fetchAt(int position) {

    }

    @Override
    public void fetchAll() {
        Call<List<Section>> call = HZJService.getService().getAllSections();
        call.enqueue(new Callback<List<Section>>() {
            @Override
            public void onResponse(Call<List<Section>> call,
                                   Response<List<Section>> response) {
                // sections = response.body();
                // for (Section section : sections)
                //     ImageManager.setImageURL(section);
                // Log.i("SectionRepository --> ", "Data is loaded....");
            }

            @Override
            public void onFailure(Call<List<Section>> call, Throwable t) {

            }
        });
    }
}
