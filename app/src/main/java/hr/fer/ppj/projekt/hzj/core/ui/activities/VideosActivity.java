package hr.fer.ppj.projekt.hzj.core.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.FavoritesRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.models.business.Video;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideosActivity extends AppCompatActivity {
    Toolbar toolbar;
    HZJContext dataContext;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        // Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        dataContext = new HZJContext(this);
        id = intent.getIntExtra("SectionID", 0);    // 0 is default
        // Log.i("sectionID = ", String.valueOf(id));
        dataContext.Videos().fetchAt(id);
        setupToolbar(intent.getStringExtra("SectionName"));
        setUpRecyclerView(this.getWindow().getDecorView().getRootView());
    }

    private void setupToolbar(String name) {
        // setting custom toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        // bind custom toolbar to this activity
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view
                .findViewById(R.id.section_videos_recycler_view);
        final FavoritesRecyclerAdapter videosRecyclerAdapter = new FavoritesRecyclerAdapter(
                getBaseContext()
        );
        // Log.i("video --> ", dataContext.Videos().get(0).getUrl());
        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        // recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(videosRecyclerAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBaseContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // API CALL
        Call<List<Video>> call = HZJService.getService().getVideosFromSelectedSection(id);
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                videosRecyclerAdapter.setAdapterData(response.body());
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
