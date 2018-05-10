package in.healthhunt.view;

import android.app.Application;
import android.content.Context;

/**
 * Created by abhishekkumar on 4/30/18.
 */

public class HealthHuntApplication extends Application {

    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getHealthHuntApplication() {
        return mContext;
    }
}
