package hr.fer.ppj.projekt.hzj.core.repositories.implementations;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.Video;
import hr.fer.ppj.projekt.hzj.core.repositories.IVideoRepository;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 21.5.2016..
 */
public class VideoRepository extends HZJRepository<Video> implements IVideoRepository {
    private List<Video> videos;

    public VideoRepository(Context context) {
        super(context);

        this.videos = new ArrayList<>();
    }

    @Override
    public void add(Video object) {
        this.videos.add(object);
    }

    @Override
    public void addSome(List<Video> objects) {
        this.videos.addAll(objects);
    }

    @Override
    public void remove(Video object) {
        this.videos.remove(object);
    }

    @Override
    public void removeSome(List<Video> objects) {
        this.videos.removeAll(objects);
    }

    @Override
    public Video get(int position) {
        return this.videos.get(position);
    }

    @Override
    public List<Video> getAll() {
        return this.videos;
    }

    // ##################### WORKING WITH DATABASE (CALLING WEB API) #####################
    @Override
    public void fetchAt(int index) {
        Log.i("VideoRepo -->", "fetch at");
        // here 'index' means ID of section...
        // Call<List<Video>> call = HZJService.getService().getVideosFromSelectedQuiz(index);
        Call<List<Video>> call = HZJService.getService().getVideosFromSelectedSection(index);
        Log.i("VideoRepo -->", "postavljen upit...");
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                videos = response.body();
                Log.i("VideoRepo   -->", "stvorio listu...");
                Log.i("videos size -->", String.valueOf(videos.size()));
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });
    }

    @Override
    public void fetchAll() {
        Call<List<Video>> call = HZJService.getService().getAllVideos();
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                videos = response.body();
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });
    }
}
