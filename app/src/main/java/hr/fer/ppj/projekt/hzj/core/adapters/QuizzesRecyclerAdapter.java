package hr.fer.ppj.projekt.hzj.core.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.cache.QuizzesCache;
import hr.fer.ppj.projekt.hzj.core.cache.UserCache;
import hr.fer.ppj.projekt.hzj.core.helpers.IObserveUser;
import hr.fer.ppj.projekt.hzj.core.models.business.Quiz;
import hr.fer.ppj.projekt.hzj.core.models.business.QuizResult;
import hr.fer.ppj.projekt.hzj.core.models.business.User;
import hr.fer.ppj.projekt.hzj.core.services.ImageManager;
import hr.fer.ppj.projekt.hzj.core.ui.activities.QuizVideosActivity;

/**
 * Created by ANTE on 22.5.2016..
 */
public class QuizzesRecyclerAdapter
        extends RecyclerView.Adapter<QuizzesRecyclerAdapter.QuizViewHolder> {
    private List<Quiz> quizzes;
    private LayoutInflater layoutInflater;
    private Context activityContext;

    public QuizzesRecyclerAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.activityContext = context;
    }

    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_quiz, parent, false);
        QuizViewHolder quizViewHolder = new QuizViewHolder(view);

        return quizViewHolder;
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        Quiz currentQuiz = quizzes.get(position);
        holder.setData(currentQuiz, position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        if (quizzes == null)
            return 0;

        return quizzes.size();
    }

    public void setAdapterData(List<Quiz> sections) {
        this.quizzes = new ArrayList<Quiz>();
        this.quizzes.addAll(sections);
        for (Quiz quizzes : this.quizzes)
            ImageManager.setQuizImageURL(quizzes);
        notifyDataSetChanged();
    }

    public void updateThatUserDataCame() {
        // WE RENDER 2 TIMES MORE...
        this.quizzes.add(new Quiz());
        notifyDataSetChanged();
        this.quizzes.remove(quizzes.size() - 1);
        notifyDataSetChanged();
    }

    public void getSectionVideos(int position) {
        Intent intent = new Intent(this.activityContext, QuizVideosActivity.class);  // ili uzeti context roditelja fragmenta...
        intent.putExtra("QuizID", this.quizzes.get(position).getId());
        intent.putExtra("QuizName", this.quizzes.get(position).getTitle());
        activityContext.startActivity(intent);
        // Log.i("start activity id=", String.valueOf(current.getId()));
    }

    class QuizViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, IObserveUser {
        private TextView title, description;
        private ImageView backgroundImage;
        private int position;
        private Quiz current;

        private TextView userQuizResult;
        private CardView userCardView;

        public QuizViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.title_of_quiz);
            this.description = (TextView) itemView.findViewById(R.id.description_of_quiz);
            this.backgroundImage = (ImageView) itemView.findViewById(R.id.image_quiz);

            this.userQuizResult = (TextView) itemView.findViewById(R.id.user_quiz_result);
            this.userCardView = (CardView) itemView.findViewById(R.id.user_result_card_view);
        }

        public void setData(Quiz currentQuiz, int position) {
            this.title.setText(currentQuiz.getTitle());
            this.description.setText(currentQuiz.getDescription());
            Picasso.with(activityContext)
                    .load(currentQuiz.getBackgroundImageURL())
                    .fit()
                    .centerCrop()
                    .into(this.backgroundImage);
            this.position = position;
            this.current = currentQuiz;

            // if user is logged in, set it's result to corresponding quiz
            if (UserCache.getUser() != null) {
                // find currentQuiz in userQuizResult...
                List<QuizResult> userQuizzes = UserCache.getUser().getQuizResultList();
                // if he have any results...
                if (userQuizzes != null)
                    for (int i = 0; i < userQuizzes.size(); i++) {
                        // if it is this quiz...
                        if (userQuizzes.get(i).getQuizId() == currentQuiz.getId()) {
                            int number = UserCache.getUser()
                                    .getQuizResultList().get(i)
                                    .getNumberOfSuccessfulAnswers();
                            this.userQuizResult.setText(number + "/" + "12");
                            if (number >= 6)
                                this.userCardView.setCardBackgroundColor(Color.rgb(130, 221, 98));
                        }
                    }
            }
        }

        public void setListeners() {
            this.backgroundImage.setOnClickListener(QuizViewHolder.this);
            this.title.setOnClickListener(QuizViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_quiz:
                    getSectionVideos(position);
                    break;
                case R.id.title_of_quiz:
                    getSectionVideos(position);
                    break;
            }
        }

        @Override
        public void notifySpecificResult() {
            /*
            // if user is logged in, set it's result to corresponding quiz
            if (UserCache.getUser() != null) {
                // find currentQuiz in userQuizResult...
                List<QuizResult> userQuizzes = UserCache.getUser().getQuizResultList();
                // if he have any results...
                if (userQuizzes != null)
                    for (int i = 0; i < userQuizzes.size(); i++) {
                        // if it is this quiz...
                        if (userQuizzes.get(i).getQuizId() == currentQuiz.getId())
                            this.userQuizResult.setText(
                                    UserCache.getUser()
                                            .getQuizResultList().get(i)
                                            .getNumberOfSuccessfulAnswers() + "/"
                                            + "10");
                    }
            }
            */
        }
    }
}
