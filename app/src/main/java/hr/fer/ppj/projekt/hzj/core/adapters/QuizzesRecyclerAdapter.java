package hr.fer.ppj.projekt.hzj.core.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.models.Quiz;

/**
 * Created by ANTE on 22.5.2016..
 */
public class QuizzesRecyclerAdapter
        extends RecyclerView.Adapter<QuizzesRecyclerAdapter.QuizViewHolder> {
    private List<Quiz> quizzes;
    private LayoutInflater layoutInflater;

    public QuizzesRecyclerAdapter(Context context, List<Quiz> quizzes) {
        this.quizzes = quizzes;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_quiz, parent, false);
        QuizViewHolder quizViewHolder = new QuizViewHolder(view);

        return quizViewHolder;
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        Quiz currentQuiz = quizzes.get(position);
        holder.setData(currentQuiz, position);
    }


    class QuizViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        private ImageView backgroundImage;
        private int position;
        private Quiz current;

        public QuizViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.title_of_quiz);
            this.description = (TextView) itemView.findViewById(R.id.description_of_quiz);
            this.backgroundImage = (ImageView) itemView.findViewById(R.id.image_quiz);
        }

        public void setData(Quiz currentQuiz, int position) {
            this.title.setText(currentQuiz.getTitle());
            this.description.setText(currentQuiz.getDescription());
            // set image...
            this.position = position;
            this.current = currentQuiz;
        }
    }
}
