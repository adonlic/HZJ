package hr.fer.ppj.projekt.hzj.core.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.helpers.IObservePicker;

/**
 * Created by ANTE on 20.5.2016..
 */
public class QuizPickerRecyclerAdapter
        extends RecyclerView.Adapter<QuizPickerRecyclerAdapter.QuizPickerViewHolder> {
    private List<String> choises;
    private LayoutInflater layoutInflater;
    private Context activityContext;
    private IObservePicker observePicker;

    public QuizPickerRecyclerAdapter(Context context, IObservePicker observePicker) {
        this.layoutInflater = LayoutInflater.from(context);
        this.activityContext = context;
        this.observePicker = observePicker;
    }

    @Override
    public QuizPickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_quiz_picker, parent, false);
        QuizPickerViewHolder quizPickerViewHolder = new QuizPickerViewHolder(view);

        return quizPickerViewHolder;
    }

    @Override
    public void onBindViewHolder(QuizPickerViewHolder holder, int position) {
        String currentChoise = choises.get(position);
        holder.setData(currentChoise, position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        if (choises == null)
            return 0;

        return choises.size();
    }

    public void setAdapterData(List<String> choises) {
        this.choises = new ArrayList<String>();
        this.choises.addAll(choises);

        notifyDataSetChanged();
    }

    class QuizPickerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView choiseContainer;
        private TextView choise;
        private int position;
        private String current;

        public QuizPickerViewHolder(View itemView) {
            super(itemView);

            this.choiseContainer = (CardView) itemView.findViewById(R.id.choise_container);
            this.choise = (TextView) itemView.findViewById(R.id.choise);
        }

        public void setData(String currentChoise, int position) {
            this.choise.setText(currentChoise);
            this.position = position;
            this.current = currentChoise;
        }

        public void setListeners() {
            this.choiseContainer.setOnClickListener(QuizPickerViewHolder.this);
            this.choise.setOnClickListener(QuizPickerViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            // do something...
            observePicker.notifyPicked(current);
        }
    }
}
