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

/**
 * Created by abhishekkumar on 4/9/18.
 */

public class LoginPresenterImpl implements ILoginPresenter, ILoginInteractor.OnLoginFinishListener {

    private final String TAG = LoginPresenterImpl.class.getSimpleName();
    ILoginView ILoginView;
    ILoginInteractor ILoginInteractor;
    private Context mContext;

    public LoginPresenterImpl(Context context, ILoginView loginView, ILoginInteractor loginInteractor) {
        mContext = context;
        ILoginView = loginView;
        ILoginInteractor = loginInteractor;
    }

    @Override
    public void validateCredentialsLogIn(String username, String password) {
        if(!username.isEmpty() && !password.isEmpty()) {
            ILoginView.onShowProgress();
            //login  and check for email and password is correct or not
            ILoginView.onHideProgress();

            if(true){
                ILoginView.showLoginAlert();
            }
            ILoginInteractor.login(createLoginRequest(username,password), this);

        }
        else {
            String str = mContext.getResources().getString(R.string.email_password_blank);
            ILoginView.showToast(str);
        }
        //ILoginView.onHideProgress();
    }

    @Override
    public void validateCredentialsSignUp(String username, String password) {
        if(!username.isEmpty() && !password.isEmpty()) {
            ILoginView.onShowProgress();
            //signup
            ILoginView.onHideProgress();

            if(true){
                ILoginView.onEmailError();
            }
        }
        else {
            String str = mContext.getResources().getString(R.string.email_password_blank);
            ILoginView.showToast(str);
        }
        //ILoginView.onHideProgress();
    }

    @Override
    public void validateNewPassword(String newPassword, String repeatPassword) {
        Log.i("TAG", "password "+ newPassword);
        if(!newPassword.isEmpty() && !repeatPassword.isEmpty()) {
            if (newPassword.equals(repeatPassword)) {
                // get Username and then call login
                ILoginView.showEmailSentAlert();
                return;
                //ILoginInteractor.login(null, newPassword, this);
            }
        }
        // show alert box for wrong passwords
        String str = mContext.getResources().getString(R.string.new_password_incorrect);
        ILoginView.showToast(str);
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
}
