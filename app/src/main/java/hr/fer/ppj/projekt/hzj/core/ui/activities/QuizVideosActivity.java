package hr.fer.ppj.projekt.hzj.core.ui.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.ViewPagerAdapter;
import hr.fer.ppj.projekt.hzj.core.cache.QuizzesCache;
import hr.fer.ppj.projekt.hzj.core.helpers.IObserver;
import hr.fer.ppj.projekt.hzj.core.helpers.QuizHelper;
import hr.fer.ppj.projekt.hzj.core.models.business.Video;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.LoginFragment;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.QuizVideosFragment;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.RegisterFragment;

public class QuizVideosActivity extends AppCompatActivity implements IObserver {
    Toolbar toolbar;
    int id;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_videos);

        // Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        id = intent.getIntExtra("QuizID", 0);    // 0 is default

        setupToolbar(intent.getStringExtra("QuizName"));
        setupSlidingView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quiz, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quit_quiz:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar(String name) {
        // setting custom toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        // bind custom toolbar to this activity
        setSupportActionBar(toolbar);
    }

    private void setupSlidingView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.quiz_videos_view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(viewPagerAdapter);

        QuizHelper.fetchQuizVideos(this, id);
    }

    @Override
    public void notifyDownloaded(boolean successful) {
        if (successful) {
            // CREATE QUIZ VIDEOS FRAGMENTS
            for (int i = 0; i < QuizzesCache.getQuizVideos(id).size(); i++) {
                QuizVideosFragment quizVideosFragment = new QuizVideosFragment();
                quizVideosFragment.setVideo(QuizzesCache.getQuizVideos(id).get(i));

                // if it's last one, offer option to finish it...
                if (i == QuizzesCache.getQuizVideos(id).size() - 1)
                    quizVideosFragment.isLast(true);

                viewPagerAdapter.addFragment(
                        quizVideosFragment, "Video \'" +
                                QuizzesCache.getQuizVideos(id).get(i).getName() + "\'");
            }

            viewPagerAdapter.notifyDataSetChanged();
        }
        else
            Toast
                    .makeText(this,
                            "Nešto je pošlo po zlu ili nema traženog podatka...",
                            Toast.LENGTH_SHORT)
                    .show();
    }
}
