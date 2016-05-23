package hr.fer.ppj.projekt.hzj.core.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.SectionsRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;

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
        Log.i("Section Fragment --> ", "onCreateView");
        return view;
    }

    public void dataContextReference(HZJContext dataContext) {
        this.dataContext = dataContext;
    }

    private void setUpRecyclerView(View view) {
        Log.i("Section Fragment --> ", "setUpRecyclerView");
        RecyclerView recyclerView = (RecyclerView) view
                .findViewById(R.id.sections_recycler_view);
        dataContext.Sections().fetchAll();
        Log.i("Section Fragment --> ", "Data load passed....");
        SectionsRecyclerAdapter sectionsRecyclerAdapter = new SectionsRecyclerAdapter(
                context,
                dataContext.Sections().getAll(),
                dataContext
        );
        recyclerView.setAdapter(sectionsRecyclerAdapter);

        Log.i("Section Fragment --> ", "Adapter is set....");
        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(getActivity());
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManagerVertical);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Log.i("Section Fragment --> ", "ARecyclerView is full set!!!");
    }

    public void referenceParentContext(Context context) {
        this.context = context;
    }
}
