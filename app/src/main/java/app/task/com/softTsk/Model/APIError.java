package app.task.com.softTsk.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Raos on 6/15/2017.
 */

public class APIError {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}


