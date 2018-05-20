package in.healthhunt.view.homeScreenView;

import android.os.Bundle;

import in.healthhunt.presenter.homeScreenPresenter.IHomePresenter;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public interface IHomeView {
    void showFragment(String tag, Bundle bundle);
    void showHomeAlert(String msg);
    void finishActivity();
    void setBottomNavigation();
    void updateTitle(String msg);
    void showProgress();
    void hideProgress();
    void updateBottomNavigation();
    IHomePresenter getHomePresenter();
}
