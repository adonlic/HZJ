package hr.fer.ppj.projekt.hzj.core.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.QuizzesRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.cache.QuizzesCache;
import hr.fer.ppj.projekt.hzj.core.cache.UserCache;
import hr.fer.ppj.projekt.hzj.core.helpers.IObserveUser;
import hr.fer.ppj.projekt.hzj.core.helpers.IObserver;
import hr.fer.ppj.projekt.hzj.core.helpers.QuizHelper;
import hr.fer.ppj.projekt.hzj.core.helpers.UserHelper;
import hr.fer.ppj.projekt.hzj.core.models.business.Quiz;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizzesFragment extends Fragment implements IObserver, IObserveUser {
    boolean firstTime = true;
    QuizzesRecyclerAdapter quizzesRecyclerAdapter1;
    QuizzesRecyclerAdapter quizzesRecyclerAdapter2;
    QuizzesRecyclerAdapter quizzesRecyclerAdapter3;
    QuizzesRecyclerAdapter quizzesRecyclerAdapter4;
    QuizzesRecyclerAdapter quizzesRecyclerAdapter5;

    public QuizzesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quizzes, container, false);

        setUpRecyclerView(view);

        return view;
    }

    private void setUpRecyclerView(View view) {
        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.quiz1_recycler_view);
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.quiz2_recycler_view);
        RecyclerView recyclerView3 = (RecyclerView) view.findViewById(R.id.quiz3_recycler_view);
        RecyclerView recyclerView4 = (RecyclerView) view.findViewById(R.id.quiz4_recycler_view);
        RecyclerView recyclerView5 = (RecyclerView) view.findViewById(R.id.quiz5_recycler_view);

        quizzesRecyclerAdapter1 = new QuizzesRecyclerAdapter(getContext());
        quizzesRecyclerAdapter2 = new QuizzesRecyclerAdapter(getContext());
        quizzesRecyclerAdapter3 = new QuizzesRecyclerAdapter(getContext());
        quizzesRecyclerAdapter4 = new QuizzesRecyclerAdapter(getContext());
        quizzesRecyclerAdapter5 = new QuizzesRecyclerAdapter(getContext());

        recyclerView1.setAdapter(quizzesRecyclerAdapter1);
        recyclerView2.setAdapter(quizzesRecyclerAdapter2);
        recyclerView3.setAdapter(quizzesRecyclerAdapter3);
        recyclerView4.setAdapter(quizzesRecyclerAdapter4);
        recyclerView5.setAdapter(quizzesRecyclerAdapter5);

        LinearLayoutManager linearLayoutManagerHorizontal1 = new LinearLayoutManager(getActivity());
        linearLayoutManagerHorizontal1.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManagerHorizontal2 = new LinearLayoutManager(getActivity());
        linearLayoutManagerHorizontal2.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManagerHorizontal3 = new LinearLayoutManager(getActivity());
        linearLayoutManagerHorizontal3.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManagerHorizontal4 = new LinearLayoutManager(getActivity());
        linearLayoutManagerHorizontal4.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManagerHorizontal5 = new LinearLayoutManager(getActivity());
        linearLayoutManagerHorizontal5.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView1.setLayoutManager(linearLayoutManagerHorizontal1);
        recyclerView2.setLayoutManager(linearLayoutManagerHorizontal2);
        recyclerView3.setLayoutManager(linearLayoutManagerHorizontal3);
        recyclerView4.setLayoutManager(linearLayoutManagerHorizontal4);
        recyclerView5.setLayoutManager(linearLayoutManagerHorizontal5);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        recyclerView4.setItemAnimator(new DefaultItemAnimator());
        recyclerView5.setItemAnimator(new DefaultItemAnimator());

        // API CALL
        QuizHelper.fetchQuizzes(this);
    }

    @Override
    public void notifyDownloaded(boolean successful) {
        if (successful) {
            List<Quiz> hard1 = new ArrayList<Quiz>();
            List<Quiz> hard2 = new ArrayList<Quiz>();
            List<Quiz> hard3 = new ArrayList<Quiz>();
            List<Quiz> hard4 = new ArrayList<Quiz>();
            List<Quiz> hard5 = new ArrayList<Quiz>();

            for (Quiz quiz : QuizzesCache.getQuizzes()) {
                if (quiz.getHardenessId() == 1)
                    hard1.add(quiz);
                else if (quiz.getHardenessId() == 2)
                    hard2.add(quiz);
                else if (quiz.getHardenessId() ==3)
                    hard3.add(quiz);
                else if (quiz.getHardenessId() == 4)
                    hard4.add(quiz);
                else if (quiz.getHardenessId() == 5)
                    hard5.add(quiz);
            }

            // update adapter data
            quizzesRecyclerAdapter1.setAdapterData(hard1);
            quizzesRecyclerAdapter2.setAdapterData(hard2);
            quizzesRecyclerAdapter3.setAdapterData(hard3);
            quizzesRecyclerAdapter4.setAdapterData(hard4);
            quizzesRecyclerAdapter5.setAdapterData(hard5);
        }
        else
            Toast
                    .makeText(getContext(),
                            "Nešto je pošlo po zlu ili nema traženog podatka...",
                            Toast.LENGTH_SHORT)
                    .show();

        // API CALL FOR USER RESULTS
        if (UserCache.getUser() != null)
            UserHelper.getUserQuizResults(this);
    }

    @Override
    public void notifySpecificResult() {
        // update user quiz result to quiz list...
        quizzesRecyclerAdapter1.updateThatUserDataCame();
        quizzesRecyclerAdapter2.updateThatUserDataCame();
        quizzesRecyclerAdapter3.updateThatUserDataCame();
        quizzesRecyclerAdapter4.updateThatUserDataCame();
        quizzesRecyclerAdapter5.updateThatUserDataCame();
    }

    public void fillWithData() {
        // API CALL
        // Toast.makeText(getContext(), "Filling with data...", Toast.LENGTH_SHORT).show();
        // if one of 5 adapters in this fragment is empty, get data for all of them
        // because all 5 are empty too...
        if (!firstTime && quizzesRecyclerAdapter1.getItemCount() == 0)
            notifyDownloaded(true);
        else {
            QuizHelper.fetchQuizzes(this);
            firstTime = false;
        }
    }
}
