package hr.fer.ppj.projekt.hzj.core.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.SectionsRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.models.business.Section;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionsFragment extends Fragment {
    HZJContext dataContext;
    Context context;

    public SectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sections, container, false);
        setUpRecyclerView(view);
        // Log.i("Section Fragment --> ", "onCreateView");

        return view;
    }

    public void dataContextReference(HZJContext dataContext) {
        this.dataContext = dataContext;
    }

    private void setUpRecyclerView(View view) {
        // Log.i("Section Fragment --> ", "setUpRecyclerView() entered");
        RecyclerView recyclerView = (RecyclerView) view
                .findViewById(R.id.sections_recycler_view);
        // dataContext.Sections().fetchAll();
        // Log.i("Section Fragment --> ", "Data load passed....");
        final SectionsRecyclerAdapter sectionsRecyclerAdapter = new SectionsRecyclerAdapter(
                context
        );
        recyclerView.setAdapter(sectionsRecyclerAdapter);

        // Log.i("Section Fragment --> ", "Adapter is set....");
        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(context);
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManagerVertical.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManagerVertical);

        Call<List<Section>> call = HZJService.getService().getAllSections();
        call.enqueue(new Callback<List<Section>>() {
            @Override
            public void onResponse(Call<List<Section>> call, Response<List<Section>> response) {
                List<Section> sec = response.body();
                sec.remove(0);
                sectionsRecyclerAdapter.setAdapterData(sec);
            }

            @Override
            public void onFailure(Call<List<Section>> call, Throwable t) {

            }
        });

        // recyclerView.setItemAnimator(new DefaultItemAnimator());
        // Log.i("Section Fragment --> ", "ARecyclerView is full set!!!");
    }

    public void referenceParentContext(Context context) {
        this.context = context;
    }
}
