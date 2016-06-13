package hr.fer.ppj.projekt.hzj.core.ui.fragments;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.QuizPickerRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.cache.VideoPickerCache;
import hr.fer.ppj.projekt.hzj.core.helpers.IObserver;
import hr.fer.ppj.projekt.hzj.core.helpers.VideoPickerHelper;
import hr.fer.ppj.projekt.hzj.core.models.business.Video;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizVideosFragment extends Fragment implements IObserver {
    Video video;
    Button finishQuiz;
    boolean isLast;

    QuizPickerRecyclerAdapter quizPickerRecyclerAdapter;

    public QuizVideosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_videos, container, false);

        final VideoView videoView = (VideoView) view.findViewById(R.id.videoPlayer);
        // ListView choises = (ListView) view.findViewById(R.id.choises);
        finishQuiz = (Button) view.findViewById(R.id.finishQuiz);

        // MediaController mediaController = new MediaController(getContext());
        // mediaController.setAnchorView(videoView);
        String URL = video.getUrl();
        final Uri video = Uri.parse("http://162.243.210.30/videos/"
                + URL.replace(" ", "%20"));
        // videoView.setMediaController(null);
        videoView.setVideoURI(video);
        // videoView.requestFocus();
        /*
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!videoView.isPlaying())
                    videoView.start();
                else
                    videoView.pause();
            }
        });*/
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                videoView.start();
            }
        });
        /*
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // mp.setLooping(true);
                // mp.reset();
                videoView.start();
            }
        });*/
        /*
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (videoView.isPlaying())
                    videoView.pause();
                else
                    videoView.start();

                return true;
            }
        });*/

        if (isLast)
            finishQuiz.setVisibility(View.VISIBLE);
        /* finishQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if all is picked then calculate result...
            }
        });*/

        /*
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.choises);
        quizPickerRecyclerAdapter =
                new QuizPickerRecyclerAdapter(getContext());
        recyclerView.setAdapter(quizPickerRecyclerAdapter);
        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(getContext());
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManagerVertical);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // VideoPickerHelper.fetchVideoTitles(this);
        */
        return view;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public void isLast(boolean isLast) {
        this.isLast = isLast;
    }

    @Override
    public void notifyDownloaded(boolean successful) {
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
            if (choises.get(i) == "")
                choises.set(i, video.getName());
        }

        quizPickerRecyclerAdapter.setAdapterData(choises);
    }
}
