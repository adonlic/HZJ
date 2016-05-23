package hr.fer.ppj.projekt.hzj.core.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.FavoritesRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.models.Video;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;
import retrofit2.Call;

public class VideosActivity extends AppCompatActivity {
    Toolbar toolbar;
    HZJContext dataContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        // Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        dataContext = new HZJContext(this);
        int id = intent.getIntExtra("SectionID", 0);    // 0 is default
        Log.i("sectionID = ", String.valueOf(id));
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
        FavoritesRecyclerAdapter videosRecyclerAdapter = new FavoritesRecyclerAdapter(
                getBaseContext(),
                dataContext.Videos().getAll()
        );
        // Log.i("video --> ", dataContext.Videos().get(0).getUrl());
        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        // recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(videosRecyclerAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getBaseContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
