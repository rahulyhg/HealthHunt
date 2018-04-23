package in.healthhunt.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import in.healthhunt.R;
import in.healthhunt.model.ErrorInfo;
import in.healthhunt.model.beans.LoginRequest;
import in.healthhunt.model.beans.Utility;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class LoginPresenterImpl implements ILoginPresenter, ILoginInteractor.OnLoginFinishListener {

    private final String TAG = LoginPresenterImpl.class.getSimpleName();
    ILoginView ILoginView;
    ILoginInteractor ILoginInteractor;

    public LoginPresenterImpl(ILoginView loginView, ILoginInteractor loginInteractor) {
        ILoginView = loginView;
        ILoginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentialsLogIn(String username, String password) {
        if((username != null && !username.isEmpty())
                && (password != null && !password.isEmpty())) {
            ILoginView.onShowProgress();
            ILoginInteractor.login(createAuthCode("login"), createLoginRequest(username,password), this);
        }
        else {
            ILoginView.onHideProgress();
            ILoginView.onEmailError();
        }
        //ILoginView.onHideProgress();
    }

    @Override
    public void validateCredentialsSignUp(String username, String password) {
        if((username != null && !username.isEmpty())
                && (password != null && !password.isEmpty())) {
            ILoginView.onShowProgress();
            //ILoginInteractor.login(createLoginRequest(username, password), this);
        }
        else {
            ILoginView.onHideProgress();
            ILoginView.onEmailError();
        }
        //ILoginView.onHideProgress();
    }

    @Override
    public void validateNewPassword(String newPassword, String repeatPassword) {
        if(newPassword != null && repeatPassword != null) {
            if(newPassword.equals(repeatPassword)) {
                // get Username and then call login
                ILoginView.onShowProgress();
                //ILoginInteractor.login(null, newPassword, this);
            }
            else {
                // show alert box for wrong passwords
                ILoginView.onHideProgress();
            }
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
    public void loadFragment(String tag) {
        ILoginView.showFragment(tag);
    }

    @Override
    public Intent loginGoogle(Context context) {
        Intent intent = null;
        if(validateGoogleServerClientID(context)) {
            intent = ILoginInteractor.loginWithGmail(context);
        }
        return intent;
    }

    @Override
    public void loginFacebook(Context context) {
        ILoginInteractor.loginWithFacebook(context, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // here write code When Login successfully
                //Log.i("TAG1","Result " + loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {}

            @Override
            public void onError(FacebookException e) {
                // here write code when get error
            }
        });
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(ErrorInfo errorInfo) {

    }

    private LoginRequest createLoginRequest(String email, String password) {
        LoginRequest loginRequest = new LoginRequest();
        //loginRequest.setmEmail(email);
        //loginRequest.setmPassword(password);
        return loginRequest;
    }

    private String createAuthCode(String requestUrl) {
        String timeStamp = Utility.getTimeStamp();
        String authCode = requestUrl + "Bd6723sXcVBg12Fe" + timeStamp;
        return authCode;
    }
}
