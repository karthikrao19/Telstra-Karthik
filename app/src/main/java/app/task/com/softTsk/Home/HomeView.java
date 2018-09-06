package app.task.com.softTsk.Home;


import app.task.com.softTsk.Model.TelResponse;

/**
 * Created by ennur on 6/25/16.
 */
public interface HomeView {

    void showProgress();

    void hideProgress();

    void onResponse(TelResponse articleLst);

    void onFailure(Throwable t);


}
