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

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public void bookmark(String id, int type) {

    }

    @Override
    public void unBookmark(String id, int type) {

    }

    @Override
    public void updateBottomNavigation() {

    }
}
