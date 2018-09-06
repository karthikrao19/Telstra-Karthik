package app.task.com.softTsk.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TelResponse {

    @SerializedName("title")
    private String title;

    @SerializedName("rows")
    private List<DetailsResponse> results = null;

    public TelResponse(String title, List<DetailsResponse> results) {
        this.title = title;
        this.results = results;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DetailsResponse> getResults() {
        return results;
    }

    public void setResults(List<DetailsResponse> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Response{" +
                "title='" + title + '\'' +
                ", results=" + results +
                '}';
    }
}
