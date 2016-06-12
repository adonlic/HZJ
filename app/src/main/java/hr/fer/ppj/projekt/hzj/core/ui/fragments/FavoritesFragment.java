package hr.fer.ppj.projekt.hzj.core.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.FavoritesRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.models.business.Video;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {
    HZJContext dataContext;
    Context context;

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
        final RecyclerView recyclerView = (RecyclerView) view
                .findViewById(R.id.videos_n_favorites_recycler_view);
        // dataContext.Favorites().fetchAt(2); // HARDKODIRANO NA UserID = 2!!!
        final FavoritesRecyclerAdapter favoritesRecyclerAdapter = new FavoritesRecyclerAdapter(
                context
        );
        recyclerView.setAdapter(favoritesRecyclerAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        Call<List<Video>> call = HZJService.getService().getUserFavorites(2);       // USER.ID = 2 HARDCODED!
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                favoritesRecyclerAdapter.setAdapterData(response.body());
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });

        // recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void referenceParentContext(Context context) {
        this.context = context;
    }
}
