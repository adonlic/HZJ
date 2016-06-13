package hr.fer.ppj.projekt.hzj.core.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.FavoritesRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.cache.UserCache;
import hr.fer.ppj.projekt.hzj.core.helpers.IObserver;
import hr.fer.ppj.projekt.hzj.core.helpers.QuizHelper;
import hr.fer.ppj.projekt.hzj.core.helpers.UserHelper;
import hr.fer.ppj.projekt.hzj.core.models.business.Video;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment implements IObserver {
    FavoritesRecyclerAdapter favoritesRecyclerAdapter;

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

    private void setUpRecyclerView(View view) {
        final RecyclerView recyclerView = (RecyclerView) view
                .findViewById(R.id.videos_n_favorites_recycler_view);

        favoritesRecyclerAdapter = new FavoritesRecyclerAdapter(getContext());

        recyclerView.setAdapter(favoritesRecyclerAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void notifyDownloaded(boolean successful) {
        if (successful) {
            favoritesRecyclerAdapter.setAdapterData(UserCache.getUserFavorites());
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

        /*
        if (favoritesRecyclerAdapter.getItemCount() == 0)
            notifyDownloaded(true);
        else
            UserHelper.fetchUserFavorites(this);
            */
    }
}
