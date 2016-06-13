package hr.fer.ppj.projekt.hzj.core.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.SectionsRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.cache.SectionsCache;
import hr.fer.ppj.projekt.hzj.core.helpers.IObserver;
import hr.fer.ppj.projekt.hzj.core.helpers.QuizHelper;
import hr.fer.ppj.projekt.hzj.core.helpers.SectionsHelper;
import hr.fer.ppj.projekt.hzj.core.models.business.Section;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionsFragment extends Fragment implements IObserver {
    boolean firstTime = true;
    SectionsRecyclerAdapter sectionsRecyclerAdapter;

    public SectionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sections, container, false);

        setUpRecyclerView(view);

        return view;
    }

    private void setUpRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view
                .findViewById(R.id.sections_recycler_view);

        sectionsRecyclerAdapter = new SectionsRecyclerAdapter(getContext());

        recyclerView.setAdapter(sectionsRecyclerAdapter);

        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(getContext());
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManagerVertical.setAutoMeasureEnabled(true);

        recyclerView.setLayoutManager(linearLayoutManagerVertical);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // API CALL
        SectionsHelper.fetchSections(this);
    }

    @Override
    public void notifyDownloaded(boolean successful) {
        if (successful) {
            // we're moving 'introduction' section which is at index = 0
            SectionsCache.getSections().remove(0);
            sectionsRecyclerAdapter.setAdapterData(SectionsCache.getSections());
        }
        else
            Toast
                    .makeText(getContext(),
                            "Nešto je pošlo po zlu ili nema traženog podatka...",
                            Toast.LENGTH_SHORT)
                    .show();
    }

    public void fillWithData() {
        // API CALL
        // Toast.makeText(getContext(), "Filling with data...", Toast.LENGTH_SHORT).show();
        // if one of 5 adapters in this fragment is empty, get data for all of them
        // because all 5 are empty too...
        if (!firstTime && sectionsRecyclerAdapter.getItemCount() == 0)
            notifyDownloaded(true);
        else {
            SectionsHelper.fetchSections(this);
            firstTime = false;
        }
    }
}
