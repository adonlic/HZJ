package hr.fer.ppj.projekt.hzj.core.repositories.implementations;

import android.content.Context;

import hr.fer.ppj.projekt.hzj.core.models.business.User;
import hr.fer.ppj.projekt.hzj.core.repositories.IUserRepository;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANTE on 21.5.2016..
 */
public class UserRepository extends HZJRepository<User> implements IUserRepository {
    private User user;

    public UserRepository(Context context) {
        super(context);

        this.user = null;
    }

    @Override
    public void add(User object) {
        this.user = object;
    }

    @Override
    public void remove(User object) {
        this.user = null;
    }

    @Override
    public User get(int position) {
        return this.user;
    }

    @Override
    public void fetchAt(int index) {
        Call<User> call = HZJService.getService().getUserInfo(index);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    @Override
    public void update(int index) {
        Call<Void> call = HZJService.getService().updateUserInfo(index);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
