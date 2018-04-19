package presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import in.healthhunt.R;
import model.ErrorInfo;

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
            ILoginInteractor.login(username,password, this);
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
            ILoginInteractor.login(username,password, this);
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
                ILoginInteractor.login("", newPassword, this);
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
    public void onSuccess() {

    }

    @Override
    public void onError(ErrorInfo errorInfo) {

    }
}
