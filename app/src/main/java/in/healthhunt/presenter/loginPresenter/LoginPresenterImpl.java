package in.healthhunt.presenter.loginPresenter;

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

import framework.retrofit.RestError;
import in.healthhunt.R;
import in.healthhunt.model.login.ForgotPasswordRequest;
import in.healthhunt.model.login.LoginRequest;
import in.healthhunt.model.login.SignUpRequest;
import in.healthhunt.view.loginView.ILoginView;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class LoginPresenterImpl implements ILoginPresenter, in.healthhunt.presenter.loginPresenter.ILoginInteractor.OnLoginFinishListener, ILoginInteractor.OnPasswordChangeListener {

    private final String TAG = LoginPresenterImpl.class.getSimpleName();
    in.healthhunt.view.loginView.ILoginView ILoginView;
    ILoginInteractor ILoginInteractor;
    private Context mContext;

    public LoginPresenterImpl(Context context, ILoginView loginView, ILoginInteractor loginInteractor) {
        mContext = context;
        ILoginView = loginView;
        ILoginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentialsLogIn(String email, String password) {
        if(!email.isEmpty() && !password.isEmpty()) {
            ILoginView.onShowProgress();
            //login  and check for email and password is correct or not
            //ILoginView.onHideProgress();

//            if(true){
//                ILoginView.showLoginAlert();
//            }
            ILoginInteractor.login(mContext, createLoginRequest(email,password,null, null), this);

        }
        else {
            String str = mContext.getResources().getString(R.string.email_password_blank);
            ILoginView.showToast(str);
        }
        //ILoginView.onHideProgress();
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


//            if(true){
//                ILoginView.onEmailError();
//            }
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
    public void onSuccess() {
        ILoginView.onHideProgress();
        ILoginView.startActivity();
    }

    @Override
    public void onError(RestError errorInfo) {
        ILoginView.onHideProgress();
        ILoginView.showLoginAlert(errorInfo.getMessage());
    }

    @Override
    public void onNewUserSuccess(String msg) {
        ILoginView.onHideProgress();
        ILoginView.showLoginAlert(msg);
    }

    @Override
    public void loginGoogle(String social_token) {
        LoginRequest loginRequest = createLoginRequest(null,null, "google", social_token);
        ILoginInteractor.login(mContext, loginRequest, this);
    }

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
