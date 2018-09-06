package app.task.com.softTsk.ViewManager;

import android.app.Activity;
import android.content.Intent;

import app.task.com.softTsk.Home.HomeActivity;

/**
 * Created by Raos on 6/14/2017.
 */

public class ViewManager {

    private Intent intent;

    public ViewManager() { }

    /**
     * @param activity
     * @param intent
     */
    public void setDisplay(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    /**
     * @param activity
     * @return
     */
    public Intent gotoHomeView(Activity activity) {
        intent = new Intent(activity, HomeActivity.class);
        setDisplay(activity, intent);
        activity.finish();
        return intent;
    }

}
