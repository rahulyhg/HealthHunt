package in.healthhunt.presenter.homeScreenPresenter;

import android.content.Context;
import android.os.Bundle;

import in.healthhunt.view.homeScreenView.IHomeView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class HomePresenterImp implements IHomePresenter {

    private String TAG = HomePresenterImp.class.getSimpleName();
    private IHomeView IHomeView;
    private Context mContext;

    public HomePresenterImp(Context context, IHomeView homeView) {
        mContext =  context;
        IHomeView = homeView;
    }

    @Override
    public void loadFragment(String tag, Bundle bundle) {
        IHomeView.showFragment(tag, bundle);
    }
}
