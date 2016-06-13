package hr.fer.ppj.projekt.hzj.core.helpers;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ppj.projekt.hzj.core.cache.VideoPickerCache;
import hr.fer.ppj.projekt.hzj.core.models.business.Video;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 13.6.2016..
 */
public class VideoPickerHelper {
    public static void fetchVideoTitles(final IObserveUser observer) {
        Call<List<Video>> call = HZJService.getService().getVideoNames();
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                List<String> pickers = new ArrayList<String>();

                for (Video v : response.body())
                    pickers.add(v.getName());
                VideoPickerCache.setPickers(pickers);
                observer.notifySpecificResult();
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });
    }
}
