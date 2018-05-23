package in.healthhunt.presenter.interactor.loginInteractor;

import android.content.Context;
import android.content.Intent;

import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;

import framework.retrofit.RestError;
import in.healthhunt.model.login.ForgotPasswordRequest;
import in.healthhunt.model.login.LoginRequest;
import in.healthhunt.model.login.SignUpRequest;
import in.healthhunt.model.login.User;
import in.healthhunt.model.login.UserData;
import in.healthhunt.model.response.HHResponse;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public interface ILoginInteractor {

    interface OnLoginFinishListener {
        void onSuccess(User user);
        void onError(RestError errorInfo);
        void onNewUserSuccess(HHResponse<UserData> user);
    }

    interface OnPasswordChangeListener {
        void onChangePassword(boolean status, String email, String msg);
    }

    void login(Context context, LoginRequest loginRequest, OnLoginFinishListener loginFinishListener);
    String loginWithFacebook(Context context, FacebookCallback<LoginResult> facebookCallback);
    Intent loginWithGmail(Context context);
    void signUp(Context context, SignUpRequest signUpRequest, OnLoginFinishListener onLoginFinishListener);
    void resetLoginPassword(Context context, ForgotPasswordRequest forgotPasswordRequest, OnPasswordChangeListener onPasswordChangeListener);
}
