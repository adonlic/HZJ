package hr.fer.ppj.projekt.hzj.core.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.QuizzesRecyclerAdapter;
import hr.fer.ppj.projekt.hzj.core.models.Quiz;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizzesFragment extends Fragment {
    HZJContext dataContext;
    Context context;
    List<Quiz> quizzes;

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
        // Log.i("Quiz Frag", "setUpRecyclerView");
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
        // dataContext.Quizzes().fetchAll();
        final QuizzesRecyclerAdapter quizzesRecyclerAdapter1 = new QuizzesRecyclerAdapter(
                context
        );
        final QuizzesRecyclerAdapter quizzesRecyclerAdapter2 = new QuizzesRecyclerAdapter(
                context
        );
        final QuizzesRecyclerAdapter quizzesRecyclerAdapter3 = new QuizzesRecyclerAdapter(
                context
        );
        final QuizzesRecyclerAdapter quizzesRecyclerAdapter4 = new QuizzesRecyclerAdapter(
                context
        );
        final QuizzesRecyclerAdapter quizzesRecyclerAdapter5 = new QuizzesRecyclerAdapter(
                context
        );
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

        // API CALL
        Call<List<Quiz>> call = HZJService.getService().getAllQuizzes();
        call.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Call<List<Quiz>> call, Response<List<Quiz>> response) {
                List<Quiz> respQuizzes = response.body();
                List<Quiz> hard1 = new ArrayList<Quiz>();
                List<Quiz> hard2 = new ArrayList<Quiz>();
                List<Quiz> hard3 = new ArrayList<Quiz>();
                List<Quiz> hard4 = new ArrayList<Quiz>();
                List<Quiz> hard5 = new ArrayList<Quiz>();

                for (Quiz quiz : respQuizzes) {
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

                quizzesRecyclerAdapter1.setAdapterData(hard1);
                quizzesRecyclerAdapter2.setAdapterData(hard2);
                quizzesRecyclerAdapter3.setAdapterData(hard3);
                quizzesRecyclerAdapter4.setAdapterData(hard4);
                quizzesRecyclerAdapter5.setAdapterData(hard5);
            }

            @Override
            public void onFailure(Call<List<Quiz>> call, Throwable t) {

            }
        });

        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        recyclerView4.setItemAnimator(new DefaultItemAnimator());
        recyclerView5.setItemAnimator(new DefaultItemAnimator());
    }

    public void referenceParentContext(Context context) {
        this.context = context;
    }
}
