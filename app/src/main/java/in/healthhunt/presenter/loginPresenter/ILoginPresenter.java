package in.healthhunt.presenter.loginPresenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public interface ILoginPresenter {
    void validateCredentialsLogIn(String email, String password);
    void validateCredentialsSignUp(String username, String gender, String email, String password);
    void forgotPassword(String email, String username);
    void loadFragment(String tag, Bundle bundle);
    Intent loginGoogle(Context context);
    void loginFacebook(Context context);
    void loginGoogle(String social_token);
}
