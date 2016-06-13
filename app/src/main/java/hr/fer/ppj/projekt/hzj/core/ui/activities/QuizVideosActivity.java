package hr.fer.ppj.projekt.hzj.core.ui.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.QuizPickerRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.adapters.ViewPagerAdapter;
import hr.fer.ppj.projekt.hzj.core.cache.QuizzesCache;
import hr.fer.ppj.projekt.hzj.core.cache.VideoPickerCache;
import hr.fer.ppj.projekt.hzj.core.helpers.IObservePicker;
import hr.fer.ppj.projekt.hzj.core.helpers.IObserveUser;
import hr.fer.ppj.projekt.hzj.core.helpers.IObserver;
import hr.fer.ppj.projekt.hzj.core.helpers.QuizHelper;
import hr.fer.ppj.projekt.hzj.core.helpers.UserHelper;
import hr.fer.ppj.projekt.hzj.core.helpers.VideoPickerHelper;
import hr.fer.ppj.projekt.hzj.core.models.business.Quiz;
import hr.fer.ppj.projekt.hzj.core.models.business.QuizHasVideo;
import hr.fer.ppj.projekt.hzj.core.models.business.Video;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.LoginFragment;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.QuizVideosFragment;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.RegisterFragment;

public class QuizVideosActivity extends AppCompatActivity implements IObserver, IObserveUser,
        IObservePicker {
    Toolbar toolbar;
    VideoView videoView;
    RecyclerView recyclerView;
    QuizPickerRecyclerAdapter quizPickerRecyclerAdapter;
    TextView score;

    int id;
    List<Video> quizVideos;
    int currentVideoId;
    int number;
    int counter;

    // ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_videos);

        // Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        id = intent.getIntExtra("QuizID", 0);    // 0 is default

        setupToolbar(intent.getStringExtra("QuizName"));
        // setupSlidingView();

        videoView = (VideoView) findViewById(R.id.videoPlayer);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.choises);
        quizPickerRecyclerAdapter =new QuizPickerRecyclerAdapter(this, this);
        recyclerView.setAdapter(quizPickerRecyclerAdapter);
        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(this);
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManagerVertical);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        score = (TextView) findViewById(R.id.score);

        QuizHelper.fetchQuizVideos(this, id);
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

    /*
    private void setupSlidingView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.quiz_videos_view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(viewPagerAdapter);

        QuizHelper.fetchQuizVideos(this, id);
    }
    */

    @Override
    public void notifyDownloaded(boolean successful) {
        quizVideos = QuizzesCache
                .getQuizVideos(id);

        currentVideoId = QuizzesCache
                .getQuizzes().get(id)
                .getQuizHasVideoList().get(0)
                .getVideoId();
        number = 0;
        counter = 1;
        setVideoView(quizVideos.get(0).getId());
        // we have quiz's videos, now get all video names for pickup...
        VideoPickerHelper.fetchVideoTitles(this);

        /*
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

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.quiz_videos_view_pager, new QuizVideosFragment());
            transaction.commit();
        }
        else
            Toast
                    .makeText(this,
                            "Nešto je pošlo po zlu ili nema traženog podatka...",
                            Toast.LENGTH_SHORT)
                    .show();
                    */
    }

    @Override
    public void notifySpecificResult() {
        // now when we have videos, set list
        setChoises(quizVideos.get(0).getId());
    }

    private void setVideoView(int videoId) {
        String URL = QuizzesCache.getQuizVideos(id).get(videoId).getUrl();      // videoId to 'counter - 1'
        final Uri video = Uri.parse("http://162.243.210.30/videos/"
                + URL.replace(" ", "%20"));
        videoView.setVideoURI(video);
        videoView.start();

        /*
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.stopPlayback();

                String url = QuizzesCache.getQuizVideos(1).get(1).getUrl();
                Uri video = Uri.parse("http://162.243.210.30/videos/"
                        + url.replace(" ", "%20"));
                videoView.setVideoURI(video);
                videoView.start();
            }
        });
        */
    }

    private void setChoises(int videoId) {
        List<String> choises = new ArrayList<>();
        choises.add("");
        choises.add("");
        choises.add("");
        choises.add("");

        Random random = new Random();
        for (int i = 0; i < choises.size() - 1; i++) {
            int randPosition = 0;
            while (choises.get(randPosition) != "")
                randPosition = random.nextInt(4);
            int randWord = random.nextInt(VideoPickerCache.getPickers().size());
            choises.set(randPosition, VideoPickerCache.getPickers().get(randWord));
        }
        for (int i = 0; i < choises.size(); i++) {
            // find empty one and put true value of video...
            if (choises.get(i) == "") {
                // find video to compare
                Video video = null;

                for (Video vid : QuizzesCache.getQuizVideos(id))
                    if (vid.getId() == videoId) {
                        video = vid;
                        break;
                    }

                choises.set(i, video.getName());
            }
        }

        quizPickerRecyclerAdapter.setAdapterData(choises);
    }

    @Override
    public void notifyPicked(String current) {
        // check if we clicked right one...
        Video video = quizVideos.get(counter - 1);
        // Toast.makeText(this, "clicked = " + current + " | wanted = " + video.getName(), Toast.LENGTH_SHORT).show();
        if (video.getName() == current) {
            Toast.makeText(this, "TOČNO", Toast.LENGTH_SHORT).show();
            currentVideoId++;
            number++;

            // ----- UPDATE UI -----
            score.setText("SCORE: " + number);

            // if we counted 12 items, it's last one
            if (counter == 12) {
                // POST user score
                UserHelper.postUserQuizResults(id, number);
                return;
            }
        }
        else
            Toast.makeText(this, "NETOČNO", Toast.LENGTH_SHORT).show();

        // NOW GO TO NEXT
        videoView.stopPlayback();
        setVideoView(quizVideos.get(counter - 1).getId());
        setChoises(quizVideos.get(counter - 1).getId());

        counter++;
    }
}
