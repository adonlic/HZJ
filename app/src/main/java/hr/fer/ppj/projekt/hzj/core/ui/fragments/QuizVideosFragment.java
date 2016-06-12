package hr.fer.ppj.projekt.hzj.core.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.VideoView;

import hr.fer.ppj.projekt.hzj.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizVideosFragment extends Fragment {


    public QuizVideosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_videos, container, false);

        VideoView videoView = (VideoView) view.findViewById(R.id.videoPlayer);
        ListView choises = (ListView) view.findViewById(R.id.choises);

        return view;
    }

}
