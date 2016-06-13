package hr.fer.ppj.projekt.hzj.core.helpers;

import android.content.Context;

import java.util.List;

import hr.fer.ppj.projekt.hzj.core.cache.SectionsCache;
import hr.fer.ppj.projekt.hzj.core.models.app.HZJApplication;
import hr.fer.ppj.projekt.hzj.core.models.business.Section;
import hr.fer.ppj.projekt.hzj.core.models.business.Video;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 12.6.2016..
 */
public class SectionsHelper {
    private static Context context = HZJApplication.getContext();

    public static void fetchSections(final IObserver observer) {
        Call<List<Section>> call = HZJService.getService().getAllSections();
        call.enqueue(new Callback<List<Section>>() {
            @Override
            public void onResponse(Call<List<Section>> call, Response<List<Section>> response) {
                if (response.body() != null) {
                    SectionsCache.setSections(response.body());
                    observer.notifyDownloaded(true);
                }
                else
                    observer.notifyDownloaded(false);
            }

            @Override
            public void onFailure(Call<List<Section>> call, Throwable t) {

            }
        });
    }

    public static void fetchSectionVideos(final IObserver observer, final int sectionId) {
        Call<List<Video>> call = HZJService.getService().getVideosFromSelectedSection(sectionId);
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if (response.body() != null) {
                    SectionsCache.getSections().get(sectionId).setVideoList(response.body());
                    observer.notifyDownloaded(true);
                }
                else
                    observer.notifyDownloaded(false);
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                observer.notifyDownloaded(false);
            }
        });
    }
}
