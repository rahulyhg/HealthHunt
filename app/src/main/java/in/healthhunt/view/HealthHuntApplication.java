package in.healthhunt.view;

import android.app.Application;

/**
 * Created by abhishekkumar on 4/30/18.
 */

public class HealthHuntApplication extends Application {

    private static HealthHuntApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static HealthHuntApplication getHealthHuntApplication() {
        return mApplication;
    }
}
