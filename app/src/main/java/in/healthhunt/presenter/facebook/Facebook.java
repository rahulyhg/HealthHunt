package in.healthhunt.presenter.facebook;

import android.content.Context;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import java.util.Arrays;
import java.util.List;

/**
 * Created by abhishekkumar on 4/20/18.
 */

public class Facebook {

    private static Facebook mFacebook;
    private CallbackManager mCallbackManager;
    private Context mContext;

    private Facebook(Context context) {
        mContext = context;
    }

    public static Facebook getInstance(Context context) {

        if(mFacebook == null) {
            mFacebook = new Facebook(context);
        }
        return mFacebook;
    }

    public CallbackManager getCallbackManager(){
        if(mCallbackManager == null) {
            mCallbackManager = CallbackManager.Factory.create();
        }
        return mCallbackManager;
    }

    public LoginManager getLoginManager(){
        return com.facebook.login.LoginManager.getInstance();
    }

    public List<String> getPermissions() {
        return Arrays.asList("email", "public_profile", "user_birthday");
    }
}
