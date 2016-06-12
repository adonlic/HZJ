package hr.fer.ppj.projekt.hzj.core.adapters;

import android.content.Context;
import android.content.Intent;
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
import hr.fer.ppj.projekt.hzj.core.models.business.Section;
import hr.fer.ppj.projekt.hzj.core.services.ImageManager;
import hr.fer.ppj.projekt.hzj.core.ui.activities.VideosActivity;

/**
 * Created by ANTE on 22.5.2016..
 */
public class SectionsRecyclerAdapter
        extends RecyclerView.Adapter<SectionsRecyclerAdapter.SectionViewHolder> {
    private List<Section> sections;
    private LayoutInflater layoutInflater;
    private Context activityContext;

    public SectionsRecyclerAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.activityContext = context;
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_section, parent, false);
        SectionViewHolder sectionViewHolder = new SectionViewHolder(view);

        return sectionViewHolder;
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        Section currentSection = sections.get(position);
        holder.setData(currentSection, position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        if (sections == null)
            return 0;

        return sections.size();
    }

    public void setAdapterData(List<Section> sections) {
        this.sections = new ArrayList<Section>();
        this.sections.addAll(sections);
        for (Section section : this.sections)
            ImageManager.setSectionImageURL(section);
        notifyDataSetChanged();
    }

    public void getSectionVideos(int position) {
        // getting id of selected section...
        // dataContext.Videos().fetchAt(sections.get(position).getId());
        // after we loaded this section's videos, start new activity...
        Intent intent = new Intent(this.activityContext, VideosActivity.class);  // ili uzeti context roditelja fragmenta...
        intent.putExtra("SectionID", this.sections.get(position).getId());
        intent.putExtra("SectionName", this.sections.get(position).getName());
        activityContext.startActivity(intent);  // our videos of this section are already waiting for us! :P
        // Log.i("start activity id=", String.valueOf(current.getId()));
    }

    class SectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, description;
        private ImageView backgroundImage;
        private int position;
        private Section current;

        public SectionViewHolder(View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.title_of_section);
            this.description = (TextView) itemView.findViewById(R.id.description_of_section);
            this.backgroundImage = (ImageView) itemView.findViewById(R.id.image_section);
        }

        public void setData(Section currentSection, int position) {
            this.name.setText(currentSection.getName());
            this.description.setText(currentSection.getDescription());
            Picasso.with(activityContext)
                    .load(currentSection.getBackgroundImageURL())
                    .fit()
                    .centerCrop()
                    .into(this.backgroundImage);
            this.position = position;
            this.current = currentSection;
        }

        public void setListeners() {
            this.backgroundImage.setOnClickListener(SectionViewHolder.this);
            this.name.setOnClickListener(SectionViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_section:
                    getSectionVideos(position);
                    break;
                case R.id.title_of_section:
                    getSectionVideos(position);
                    break;
            }
        }
    }
}
