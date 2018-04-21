package in.healthhunt.presenter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.Arrays;

import in.healthhunt.R;
import in.healthhunt.presenter.facebook.Facebook;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class LoginInteractorImpl implements ILoginInteractor {
    @Override
    public void login(String userName, String password, OnLoginFinishListener loginFinishListener) {
        // implement the login code here

    }

    @Override
    public String loginWithFacebook(Context context, FacebookCallback<LoginResult> facebookCallback) {
        Facebook facebook = Facebook.getInstance(context);
        CallbackManager callbackManager = facebook.getCallbackManager();
        LoginManager loginManager = facebook.getLoginManager();
        loginManager.logInWithReadPermissions((Activity) context, facebook.getPermissions());

        loginManager.registerCallback(callbackManager, facebookCallback);

        return null;
    }

    @Override
    public Intent loginWithGmail(Context context) {
        String serverClientId = context.getResources().getString(R.string.server_client_id);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(serverClientId)
                .requestEmail()
                .build();
        return GoogleSignIn.getClient(context, gso).getSignInIntent();
    }
}
