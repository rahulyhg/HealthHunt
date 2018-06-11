package in.healthhunt.view;

import android.app.Application;

import com.activeandroid.ActiveAndroid;


/**
 * Created by abhishekkumar on 5/29/18.
 */

public class HealthHuntApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}

