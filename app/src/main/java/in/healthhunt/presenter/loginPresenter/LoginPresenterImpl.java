package in.healthhunt.presenter.loginPresenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

import java.util.Set;

import framework.retrofit.RestError;
import in.healthhunt.R;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.model.login.ForgotPasswordRequest;
import in.healthhunt.model.login.LoginRequest;
import in.healthhunt.model.login.SignUpRequest;
import in.healthhunt.model.login.User;
import in.healthhunt.model.login.UserData;
import in.healthhunt.model.preference.HealthHuntPreference;
import in.healthhunt.model.response.HHResponse;
import in.healthhunt.presenter.interactor.loginInteractor.ILoginInteractor;
import in.healthhunt.view.loginView.ILoginView;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class LoginPresenterImpl implements ILoginPresenter, ILoginInteractor.OnLoginFinishListener, ILoginInteractor.OnPasswordChangeListener {

    private final String TAG = LoginPresenterImpl.class.getSimpleName();
    in.healthhunt.view.loginView.ILoginView ILoginView;
    ILoginInteractor ILoginInteractor;
    private Context mContext;

    public LoginPresenterImpl(Activity activity, ILoginInteractor loginInteractor) {
         mContext = activity.getApplicationContext();
        ILoginView = (ILoginView) activity;
        ILoginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentialsLogIn(String email, String password) {
        if(!email.isEmpty() && !password.isEmpty()) {
            HealthHuntPreference.putBoolean(mContext, Constants.IS_LOGIN_NORMAL, true);
            ILoginView.onShowProgress();
            ILoginInteractor.login(mContext, createLoginRequest(email,password,null, null), this);
        }
        else {
            String str = mContext.getResources().getString(R.string.email_password_blank);
            ILoginView.showToast(str);
        }
    }


    @Override
    public void validateCredentialsSignUp(String username, String gender, String email, String password) {
        if(!username.isEmpty() && !password.isEmpty()) {
            ILoginView.onShowProgress();
            //signup

            if(username == null || username.isEmpty()) {
                username = email;
            }

            if(gender == null || gender.isEmpty()) {
                gender = "Not specified";
            }

            SignUpRequest signUpRequest = createSignUpRequest(username, gender, username, password);
            ILoginInteractor.signUp(mContext, signUpRequest, this);
        }
        else {
            String str = mContext.getResources().getString(R.string.email_password_blank);
            ILoginView.showToast(str);
        }
        //ILoginView.onHideProgress();
    }

    @Override
    public void forgotPassword(String email, String username) {
        if(!email.isEmpty() || !username.isEmpty()) {
            ILoginView.onShowProgress();
            ForgotPasswordRequest request = createForgotPasswordRequest(email, username);
            ILoginInteractor.resetLoginPassword(mContext, request, this);
        }
        else {
            ILoginView.showToast("Enter the email");
        }
    }

    private boolean validateGoogleServerClientID(Context context) {
        String serverClientId = context.getString(R.string.server_client_id);
        String suffix = ".apps.googleusercontent.com";
        if (!serverClientId.trim().endsWith(suffix)) {
            String message = "Invalid server client ID in strings.xml, must end with " + suffix;
            Log.d(TAG, message);
            return false;

        }
        return true;
    }

    @Override
    public void loadFragment(String tag, Bundle bundle) {
        ILoginView.showFragment(tag, bundle);
    }

    @Override
    public Intent loginGoogle(Context context) {
        Intent intent = null;
        if(validateGoogleServerClientID(context)) {
            ILoginView.onShowProgress();
            intent = ILoginInteractor.loginWithGmail(context);
        }
        return intent;
    }

    @Override
    public void loginFacebook(Context context) {
        HealthHuntPreference.putBoolean(mContext, Constants.IS_LOGIN_NORMAL, false);
        HealthHuntPreference.putString(mContext, Constants.SOCIAL_LOGIN, Constants.FACEBOOK);
        ILoginView.onShowProgress();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        String token;
        if(accessToken != null && accessToken.getToken() != null) {
            token = accessToken.getToken();
            LoginRequest loginRequest = createLoginRequest(null, null, "facebook", token);
            ILoginInteractor.login(mContext, loginRequest, LoginPresenterImpl.this);
        }
        else {
            ILoginInteractor.loginWithFacebook(context, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    // here write code When Login successfully
                    String token = loginResult.getAccessToken().getToken();
                    Log.i("TAG1", "Result " + token);
                    LoginRequest loginRequest = createLoginRequest(null, null, "facebook", token);
                    //ILoginView.onHideProgress();
                    ILoginInteractor.login(mContext, loginRequest, LoginPresenterImpl.this);
                }

                @Override
                public void onCancel() {
                    Log.i("TAG1", "onCancel ");
                    ILoginView.onHideProgress();
                }

                @Override
                public void onError(FacebookException e) {
                    ILoginView.onHideProgress();
                    Log.i("TAG1", "Facebook Error " + e);
                }
            });
        }
    }

    @Override
    public void onSuccess(User user) {
        ILoginView.onHideProgress();
        storeUserInfo(user);

        Set<String> tags = HealthHuntPreference.getSet(mContext, Constants.SELECTED_TAGS_KEY);
        if(tags != null && !tags.isEmpty()) {
            HealthHuntPreference.putBoolean(mContext, Constants.IS_LOGIN_FIRST_KEY, true);
            ILoginView.startHomeActivity();
        }
        else {
            ILoginView.startTagActivity();
        }
    }

    private void storeUserInfo(User user) {
        Log.i("TAG", "IMAGE " + user.getUser_image() + " ID " + user.getId());
        Log.i("TAG", "URL " + user.getUrl());
        HealthHuntPreference.putString(mContext, Constants.USER_ID, String.valueOf(user.getId()));
        HealthHuntPreference.putString(mContext, Constants.USER_NAME, String.valueOf(user.getName()));
        HealthHuntPreference.putString(mContext, Constants.USER_URL, String.valueOf(user.getUrl()));
    }

    @Override
    public void onError(RestError errorInfo) {
        ILoginView.onHideProgress();
        HealthHuntPreference.clear(mContext);
        if(errorInfo != null) {
            ILoginView.showLoginAlert(errorInfo.getMessage());
        }
    }

    @Override
    public void onNewUserSuccess(HHResponse<UserData> user) {
        ILoginView.onHideProgress();
        storeUserInfo(user.getData().getUser());
        ILoginView.showLoginAlert(user.getMessage());
    }

    @Override
    public void loginGoogle(String social_token) {
        HealthHuntPreference.putBoolean(mContext, Constants.IS_LOGIN_NORMAL, false);
        HealthHuntPreference.putString(mContext, Constants.SOCIAL_LOGIN, Constants.GOOGLE);
        ILoginView.onShowProgress();
        LoginRequest loginRequest = createLoginRequest(null,null, "google", social_token);
        ILoginInteractor.login(mContext, loginRequest, this);
    }

    /*@Override
    public void alreadyLogin() {

        boolean isNormalLogin = HealthHuntPreference.getBoolean(mContext, Constants.IS_LOGIN_NORMAL);

        if(isNormalLogin) {
            String email = HealthHuntPreference.getString(mContext, Constants.EMAIL);
            String password = HealthHuntPreference.getString(mContext, Constants.PASSWORD);
            validateCredentialsLogIn(email, password);
        }
        else {
            String socialLogin = HealthHuntPreference.getString(mContext, Constants.SOCIAL_LOGIN);
            if(Constants.FACEBOOK.equalsIgnoreCase(socialLogin)){
                loginFacebook(mContext);
            }
            else {
                Intent intent = new Intent(mContext, GoogleLoginActivity.class);
                if(intent != null) {
                    mActivity.startActivityForResult(intent, Constants.GMAIL_REQUEST_CODE);
                }
            }
        }
    }*/


    @Override
    public void onChangePassword(boolean status, String email, String msg) {
        ILoginView.onHideProgress();
        Spannable spannable = new SpannableString(msg);
        Log.i("TAGWW", "Email " + email);
        if(status) {
            String linkSent = mContext.getResources().getString(R.string.link_sent);
            msg = linkSent + " " + email;
            spannable = new SpannableString(msg);
            spannable.setSpan(new ForegroundColorSpan(Color.BLACK),
                    linkSent.length() + 1 , msg.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        ILoginView.showPasswordChangeAlert(spannable);
    }

    private SignUpRequest createSignUpRequest(String username, String gender, String email, String password) {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setmUserName(username);
        signUpRequest.setmGender(gender);
        signUpRequest.setmEmail(email);
        signUpRequest.setmPassword(password);
        return signUpRequest;
    }

    private LoginRequest createLoginRequest(String email, String password, String social, String social_token) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setmEmail(email);
        loginRequest.setmPassword(password);
        loginRequest.setmSocialNetwork(social);
        loginRequest.setmSocialToken(social_token);
        return loginRequest;
    }

    private ForgotPasswordRequest createForgotPasswordRequest(String email, String userName) {
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
        forgotPasswordRequest.setmEmail(email);
        forgotPasswordRequest.setmUsername(userName);
        return forgotPasswordRequest;
    }
}
