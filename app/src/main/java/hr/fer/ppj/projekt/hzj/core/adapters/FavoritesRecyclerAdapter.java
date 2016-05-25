package hr.fer.ppj.projekt.hzj.core.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.models.Section;
import hr.fer.ppj.projekt.hzj.core.models.Video;
import hr.fer.ppj.projekt.hzj.core.services.ImageManager;
import hr.fer.ppj.projekt.hzj.core.ui.activities.PlayVideoActivity;
import hr.fer.ppj.projekt.hzj.core.ui.activities.VideosActivity;

/**
 * Created by ANTE on 22.5.2016..
 */
public class FavoritesRecyclerAdapter
        extends RecyclerView.Adapter<FavoritesRecyclerAdapter.FavoriteViewHolder> {
    private List<Video> videos;
    private LayoutInflater layoutInflater;
    private Context activityContext;

    public FavoritesRecyclerAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.activityContext = context;
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_video, parent, false);              // PROMIJENITI
        FavoriteViewHolder favoriteViewHolder = new FavoriteViewHolder(view);

        return favoriteViewHolder;
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        Video currentFavorite = videos.get(position);
        holder.setData(currentFavorite, position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        if (videos == null)
            return 0;

        return videos.size();
    }

    public void setAdapterData(List<Video> videos) {
        this.videos = new ArrayList<Video>();
        this.videos.addAll(videos);
        // for (Video video : this.videos)
        //     ImageManager.setSectionImageURL(section);
        /*
        for (Video video : this.videos) {
            int id = video.getSectionId();

            if (id == 1 || id == 2 || id == 8 || id == 9 || id == 19)
                video.setBackgroundImage(context.getResources().getDrawable(R.drawable.thumb1));
            else if (id == 3 || id == 4 || id == 6 || id == 7 || id == 10 || id == 11)
                video.setBackgroundImage(context.getResources().getDrawable(R.drawable.thumb2));
            else if (id == 5 || id == 13 || id == 15 || id == 17 || id == 20)
                video.setBackgroundImage(context.getResources().getDrawable(R.drawable.thumb3));
            else if (id == 12 || id == 14 || id == 16 || id == 18)
                video.setBackgroundImage(context.getResources().getDrawable(R.drawable.thumb4));
        }
        */
        //    ImageManager.setVideoImage(video, context);
        notifyDataSetChanged();
    }

    public void getSectionVideos(int position) {
        // after we loaded this section's videos, start new activity...
        Intent intent = new Intent(this.activityContext, PlayVideoActivity.class);  // ili uzeti context roditelja fragmenta...
        intent.putExtra("VideoID", this.videos.get(position).getId());
        intent.putExtra("VideoName", this.videos.get(position).getName());
        intent.putExtra("VideoURL", this.videos.get(position).getUrl());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.i("setSectionVideo()", " u favorites...prije otvaranja");
        activityContext.startActivity(intent);  // our videos of this section are already waiting for us! :P
        // Log.i("start activity id=", String.valueOf(current.getId()));
    }


    class FavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {                                      // PROVJERITI NAKON PROMJENE LAYOUTA
        private TextView name;
        private ImageView backgroundImage;
        private int position;
        private Video current;

        public FavoriteViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.title_of_video);
            backgroundImage = (ImageView) itemView.findViewById(R.id.image_video);
        }

        public void setData(Video currentFavorite, int position) {
            this.name.setText(currentFavorite.getName());
            // backgroundImage.setBackground(currentFavor);


            //this.backgroundImage.setBackground(currentFavorite.getBackgroundImage());
            int id = currentFavorite.getSectionId();
            if (id == 1 || id == 2 || id == 8 || id == 9 || id == 19)
                this.backgroundImage.setImageResource(R.drawable.thumb1);
            else if (id == 3 || id == 4 || id == 6 || id == 7 || id == 10 || id == 11)
                this.backgroundImage.setImageResource(R.drawable.thumb2);
            else if (id == 5 || id == 13 || id == 15 || id == 17 || id == 20)
                this.backgroundImage.setImageResource(R.drawable.thumb3);
            else if (id == 12 || id == 14 || id == 16 || id == 18)
                this.backgroundImage.setImageResource(R.drawable.thumb4);


            this.position = position;
            this.current = currentFavorite;
        }

        public void setListeners() {
            this.backgroundImage.setOnClickListener(FavoriteViewHolder.this);
            this.name.setOnClickListener(FavoriteViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_video:
                    getSectionVideos(position);
                    break;
                case R.id.title_of_video:
                    getSectionVideos(position);
                    break;
            }
        }
    }
}
