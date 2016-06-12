package hr.fer.ppj.projekt.hzj.core.ui.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.ViewPagerAdapter;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.LoginFragment;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.QuizVideosFragment;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.RegisterFragment;

public class QuizVideosActivity extends AppCompatActivity {
    Toolbar toolbar;
    HZJContext dataContext;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_videos);

        // Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        dataContext = new HZJContext(this);
        id = intent.getIntExtra("QuizID", 0);    // 0 is default
        // Log.i("sectionID = ", String.valueOf(id));
        // dataContext.Quizzes().fetchAt(id);
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
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // QUIZ VIDEOS FRAGMENTS
        QuizVideosFragment quizVideosFragment1 = new QuizVideosFragment();
        QuizVideosFragment quizVideosFragment2 = new QuizVideosFragment();

        viewPagerAdapter.addFragment(quizVideosFragment1, "Video 1");
        viewPagerAdapter.addFragment(quizVideosFragment2, "Video 2");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
