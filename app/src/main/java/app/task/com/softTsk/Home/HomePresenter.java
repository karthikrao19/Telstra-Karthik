package app.task.com.softTsk.Home;

import android.util.Log;

import app.task.com.softTsk.Model.TelResponse;
import app.task.com.softTsk.Network.ApiClient;
import app.task.com.softTsk.Network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Raos
 */

public class HomePresenter implements HomePresenterInfc{

    private HomeView homeView = new HomeActivity() ;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void onLoadData() {
        try {

            if (homeView != null) {
                homeView.showProgress();
                ApiInterface apiService = ApiClient.createService(ApiInterface.class);
                Call<TelResponse> call = apiService.getArticles();
                Log.e("TAG**URL", call.request().url().toString());
                call.enqueue(new Callback<TelResponse>() {
                    @Override
                    public void onResponse(Call<TelResponse> call, Response<TelResponse> response) {

                        TelResponse articleList = response.body();

                        homeView.hideProgress();

                        homeView.onResponse(articleList);
                    }

                    @Override
                    public void onFailure(Call<TelResponse> call, Throwable t) {
                        // the network call was a failure
                        homeView.hideProgress();
                        homeView.onFailure(t);
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        homeView = null;
    }


}
