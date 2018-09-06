package app.task.com.softTsk.Network;

import android.app.Activity;

import java.io.IOException;
import java.lang.annotation.Annotation;

import app.task.com.softTsk.Model.APIError;
import app.task.com.softTsk.R;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by Raos on 6/15/2017.
 */

public class ErrorUtils {

    public static APIError parseError(Response<?> response) {
        Converter<ResponseBody, APIError> converter = ApiClient.retrofit()
                        .responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }


    public static String responseError(Activity activity, Throwable t) {
        String error;
        if (t instanceof IOException) {
            error =  activity.getResources().getString(R.string.noInternetConnection);
        } else {
            error =  activity.getResources().getString(R.string.pleaseTryAgain);
        }
        return error;
    }
}

