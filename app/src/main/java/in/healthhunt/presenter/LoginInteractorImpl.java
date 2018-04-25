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

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.R;
import in.healthhunt.model.beans.LoginRequest;
import in.healthhunt.model.beans.LoginResponse;
import in.healthhunt.presenter.facebook.Facebook;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class LoginInteractorImpl implements ILoginInteractor {
    @Override
    public void login(LoginRequest loginRequest, OnLoginFinishListener loginFinishListener) {
        WebServicesWrapper.getInstance().login(loginRequest, new ResponseResolver<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse loginResponse, Response response) {
                Log.i("TAGLoginInte", "response " + loginResponse + "Response " + response);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                Log.i("TAGLoginInte", "Failure " + error.getMessage());
            }
        });
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
