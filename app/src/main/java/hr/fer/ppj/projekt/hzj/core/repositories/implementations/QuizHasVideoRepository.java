package hr.fer.ppj.projekt.hzj.core.repositories.implementations;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.models.QuizHasVideo;
import hr.fer.ppj.projekt.hzj.core.models.Video;
import hr.fer.ppj.projekt.hzj.core.repositories.IQuizHasVideoRepository;
import hr.fer.ppj.projekt.hzj.core.repositories.IVideoRepository;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 21.5.2016..
 */
public class QuizHasVideoRepository extends HZJRepository<Video>
        implements IVideoRepository {
    private List<Video> videos;

    public QuizHasVideoRepository(Context context) {
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

    @Override
    public void fetchAt(int index) {
        // position here is ID of quiz
        Call<List<Video>> call = HZJService.getService().getVideosFromSelectedQuiz(index);
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
