package in.healthhunt.presenter;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public interface ILoginPresenter {
    void validateCredentialsLogIn(String username, String password);
    void validateCredentialsSignUp(String username, String password);
    void validateNewPassword(String newPassword, String repeatPassword);
    void loadFragment(String tag);
}
