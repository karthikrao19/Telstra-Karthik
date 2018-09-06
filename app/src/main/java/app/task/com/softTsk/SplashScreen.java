package app.task.com.softTsk;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import app.task.com.softTsk.ViewManager.ViewManager;

public class SplashScreen extends AppCompatActivity {

    // Splash screen timer
    private int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        showSplashScreen();

    }

    private void showSplashScreen() {
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                new ViewManager().gotoHomeView(SplashScreen.this);

            }
        }, SPLASH_TIME_OUT);
    }

}
