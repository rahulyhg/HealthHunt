package in.healthhunt.presenter.loginPresenter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.R;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.model.login.ForgotPasswordRequest;
import in.healthhunt.model.login.LoginRequest;
import in.healthhunt.model.login.SignUpRequest;
import in.healthhunt.model.login.User;
import in.healthhunt.presenter.preference.HealthHuntPreference;
import okhttp3.Headers;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class LoginInteractorImpl implements ILoginInteractor {
    @Override
    public void login(final Context context, LoginRequest loginRequest, final OnLoginFinishListener loginFinishListener) {
        WebServicesWrapper.getInstance().login(loginRequest, new ResponseResolver<User>() {
            @Override
            public void onSuccess(User user, Response response) {
                storeSessionToken(context, response);
                loginFinishListener.onSuccess();
                Log.i("TAGLoginInte", "response " + user + "Response " + response);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                Log.i("TAG", "msg " + msg);
                loginFinishListener.onError(error);
            }
        });
        // implement the login code here

    }

    private void storeSessionToken(Context context, Response response) {
        Headers header = response.headers();
        String token = header.get(Constants.SESSION_TOKEN);
        if(token != null) {
            Log.i("TAGTOKEN", "Session Token " + token);
            HealthHuntPreference.putString(context, Constants.SESSION_TOKEN, token);
        }
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


    @Override
    public void signUp(final Context context, SignUpRequest signUpRequest, final OnLoginFinishListener onLoginFinishListener) {
            WebServicesWrapper.getInstance().signUp(signUpRequest, new ResponseResolver<User>() {
                @Override
                public void onSuccess(User user, Response response) {
                    storeSessionToken(context, response);
                    onLoginFinishListener.onSuccess();
                }

                @Override
                public void onFailure(RestError error, String msg) {
                    onLoginFinishListener.onError(error);
                }
            });
    }

    @Override
    public void resetLoginPassword(final ForgotPasswordRequest forgotPasswordRequest, final OnPasswordChangeListener passwordChangeListener) {
            WebServicesWrapper.getInstance().forgotPassword(forgotPasswordRequest, new ResponseResolver<String>() {
                @Override
                public void onSuccess(String s, Response response) {
                    passwordChangeListener.onChangePassword(true, forgotPasswordRequest.getmEmail(), response.message());
                }

                @Override
                public void onFailure(RestError error, String msg) {
                    Log.i("TAG", "resonse onFailure" + msg);
                    passwordChangeListener.onChangePassword(false, null, error.getMessage());
                }
            });
    }
}
