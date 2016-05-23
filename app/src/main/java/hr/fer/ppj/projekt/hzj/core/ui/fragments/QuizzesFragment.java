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

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.QuizzesRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.models.Quiz;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizzesFragment extends Fragment {
    HZJContext dataContext;

    public QuizzesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("Quiz Frag", "onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quizzes, container, false);
        setUpRecyclerView(view);

        return view;
    }

    public void dataContextReference(HZJContext dataContext) {
        this.dataContext = dataContext;
    }

    private void setUpRecyclerView(View view) {
        Log.i("Quiz Frag", "setUpRecyclerView");
        RecyclerView recyclerView1 = (RecyclerView) view
                .findViewById(R.id.quiz1_recycler_view);
        RecyclerView recyclerView2 = (RecyclerView) view
                .findViewById(R.id.quiz2_recycler_view);
        RecyclerView recyclerView3 = (RecyclerView) view
                .findViewById(R.id.quiz3_recycler_view);
        RecyclerView recyclerView4 = (RecyclerView) view
                .findViewById(R.id.quiz4_recycler_view);
        RecyclerView recyclerView5 = (RecyclerView) view
                .findViewById(R.id.quiz5_recycler_view);
        dataContext.Quizzes().fetchAll();
        QuizzesRecyclerAdapter sectionsRecyclerAdapter1 = new QuizzesRecyclerAdapter(
                getContext(),
                dataContext.Quizzes().getByHardness(1)
        );
        QuizzesRecyclerAdapter sectionsRecyclerAdapter2 = new QuizzesRecyclerAdapter(
                getContext(),
                dataContext.Quizzes().getByHardness(2)
        );
        QuizzesRecyclerAdapter sectionsRecyclerAdapter3 = new QuizzesRecyclerAdapter(
                getContext(),
                dataContext.Quizzes().getByHardness(3)
        );
        QuizzesRecyclerAdapter sectionsRecyclerAdapter4 = new QuizzesRecyclerAdapter(
                getContext(),
                dataContext.Quizzes().getByHardness(4)
        );
        QuizzesRecyclerAdapter sectionsRecyclerAdapter5 = new QuizzesRecyclerAdapter(
                getContext(),
                dataContext.Quizzes().getByHardness(5)
        );
        recyclerView1.setAdapter(sectionsRecyclerAdapter1);
        recyclerView2.setAdapter(sectionsRecyclerAdapter2);
        recyclerView3.setAdapter(sectionsRecyclerAdapter3);
        recyclerView4.setAdapter(sectionsRecyclerAdapter4);
        recyclerView5.setAdapter(sectionsRecyclerAdapter5);

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
    }
}
