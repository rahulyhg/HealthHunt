package in.healthhunt.presenter;

/**
 * Created by abhishekkumar on 4/9/18.
 */

public interface ILoginView {

    void onShowProgress();
    void onHideProgress();
    void onEmailError();
    void onPasswordError();
    void showFragment(String tag);
}
