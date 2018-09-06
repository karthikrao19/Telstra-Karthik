package app.task.com.softTsk.Network;

import app.task.com.softTsk.Model.TelResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {

    @Headers("Content-Type: application/json; charset=utf-8")
    @GET("2iodh4vg0eortkl/facts.js")
    Call<TelResponse> getArticles();

}
