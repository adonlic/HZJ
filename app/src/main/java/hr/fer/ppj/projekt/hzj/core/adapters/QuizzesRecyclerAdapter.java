package hr.fer.ppj.projekt.hzj.core.adapters;

import android.content.Context;
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
import hr.fer.ppj.projekt.hzj.core.models.business.Quiz;
import hr.fer.ppj.projekt.hzj.core.services.ImageManager;

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
            Picasso.with(activityContext)
                    .load(currentQuiz.getBackgroundImageURL())
                    .fit()
                    .centerCrop()
                    .into(this.backgroundImage);
            this.position = position;
            this.current = currentQuiz;
        }
    }
}
