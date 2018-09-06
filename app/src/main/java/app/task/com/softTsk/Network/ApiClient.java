package app.task.com.softTsk.Network;

import app.task.com.softTsk.BuildConfig;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Raos on 6/16/2017.
 */

public class ApiClient {
    private static Retrofit retrofit;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    public static Retrofit retrofit(){
        return retrofit;
    }


    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> aClass) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        // add logging as last interceptor
        httpClient.addInterceptor(logging);
        retrofit = retrofitBuilder.client(httpClient.build()).build();
        return retrofit.create(aClass);
    }


    public static RequestBody getRequestBody(String params) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), params);
    }

}
