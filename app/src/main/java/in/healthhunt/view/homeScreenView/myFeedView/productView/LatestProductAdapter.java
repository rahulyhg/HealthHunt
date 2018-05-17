package in.healthhunt.view.homeScreenView.myFeedView.productView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.productPresenter.IProductPresenter;


/**
 * Created by abhishekkumar on 4/24/18.
 */

public class LatestProductAdapter extends FragmentStatePagerAdapter {

    private IProductPresenter IProductPresenter;
    private int mType;

    public LatestProductAdapter(FragmentManager fragmentManager, IProductPresenter latestProductPresenter, int type) {
        super(fragmentManager);
        IProductPresenter = latestProductPresenter;
        mType = type;
    }
    @Override
    public Fragment getItem(int position) {
        return IProductPresenter.getItem(position, mType);
    }

    @Override
    public int getCount() {
        return IProductPresenter.getProductCount();
    }

    /*@Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }*/
}
