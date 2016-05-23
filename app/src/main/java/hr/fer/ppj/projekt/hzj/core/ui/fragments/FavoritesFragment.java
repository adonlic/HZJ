package hr.fer.ppj.projekt.hzj.core.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.FavoritesRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.adapters.SectionsRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.models.Favorite;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {
    HZJContext dataContext;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos_n_favorites, container, false);
        setUpRecyclerView(view);

        return view;
    }

    public void dataContextReference(HZJContext dataContext) {
        this.dataContext = dataContext;
    }

    private void setUpRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view
                .findViewById(R.id.videos_n_favorites_recycler_view);
        dataContext.Favorites().fetchAt(2); // HARDKODIRANO NA UserID = 2!!!
        FavoritesRecyclerAdapter favoritesRecyclerAdapter = new FavoritesRecyclerAdapter(
                getContext(),
                dataContext.Favorites().getAll()
        );
        recyclerView.setAdapter(favoritesRecyclerAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
