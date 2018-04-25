package in.healthhunt.presenter;
import android.content.Context;
import android.content.Intent;

import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;

import in.healthhunt.model.ErrorInfo;
import in.healthhunt.model.beans.LoginRequest;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public interface ILoginInteractor {

    interface OnLoginFinishListener {
        void onSuccess();
        void onError(ErrorInfo errorInfo);
    }

    void login(LoginRequest loginRequest, OnLoginFinishListener loginFinishListener);
    String loginWithFacebook(Context context, FacebookCallback<LoginResult> facebookCallback);
    Intent loginWithGmail(Context context);
}
