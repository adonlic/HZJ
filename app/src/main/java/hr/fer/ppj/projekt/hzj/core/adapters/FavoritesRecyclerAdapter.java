package hr.fer.ppj.projekt.hzj.core.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.models.Section;
import hr.fer.ppj.projekt.hzj.core.models.Video;

/**
 * Created by ANTE on 22.5.2016..
 */
public class FavoritesRecyclerAdapter
        extends RecyclerView.Adapter<FavoritesRecyclerAdapter.FavoriteViewHolder> {
    private List<Video> videos;
    private LayoutInflater layoutInflater;

    public FavoritesRecyclerAdapter(Context context, List<Video> videos) {
        this.videos = videos;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_section, parent, false);              // PROMIJENITI
        FavoriteViewHolder favoriteViewHolder = new FavoriteViewHolder(view);

        return favoriteViewHolder;
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        Video currentFavorite = videos.get(position);
        holder.setData(currentFavorite, position);
    }


    class FavoriteViewHolder extends RecyclerView.ViewHolder {                                      // PROVJERITI NAKON PROMJENE LAYOUTA
        private TextView name;
        private ImageView backgroundImage;
        private int position;
        private Video current;

        public FavoriteViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.title_of_section);
            backgroundImage = (ImageView) itemView.findViewById(R.id.image_section);
        }

        public void setData(Video currentFavorite, int position) {
            this.name.setText(currentFavorite.getName());
            // backgroundImage.setBackground(currentFavor);
            this.position = position;
            this.current = currentFavorite;
        }
    }
}
