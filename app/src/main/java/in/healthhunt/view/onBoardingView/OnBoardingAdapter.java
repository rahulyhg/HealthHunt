package in.healthhunt.view.onBoardingView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.healthhunt.presenter.onBoardingPresenter.IOnBoardingPresenter;

/**
 * Created by abhishekkumar on 4/24/18.
 */

public class OnBoardingAdapter extends FragmentPagerAdapter {

    private IOnBoardingPresenter IOnBoardingPresenter;

    public OnBoardingAdapter(FragmentManager fragmentManager, IOnBoardingPresenter boardingPresenter) {
        super(fragmentManager);
        IOnBoardingPresenter = boardingPresenter;
    }
    @Override
    public Fragment getItem(int position) {
        return IOnBoardingPresenter.getItem(position);
    }

    @Override
    public int getCount() {
        return IOnBoardingPresenter.getCount();
    }
}
