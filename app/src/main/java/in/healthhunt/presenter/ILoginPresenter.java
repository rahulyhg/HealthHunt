package in.healthhunt.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public interface ILoginPresenter {
    void validateCredentialsLogIn(String username, String password);
    void validateCredentialsSignUp(String username, String password);
    void validateNewPassword(String newPassword, String repeatPassword, String email, String username);
    void loadFragment(String tag, Bundle bundle);
    Intent loginGoogle(Context context);
    void loginFacebook(Context context);
    void loginGoogle(String social_token);
}
