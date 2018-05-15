package in.healthhunt.view.homeScreenView.article.myfeed;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.ILatestProductPresenter;


/**
 * Created by abhishekkumar on 4/24/18.
 */

public class LatestProductAdapter extends FragmentStatePagerAdapter {

    private ILatestProductPresenter ILatestProductPresenter;
    private int mType;

    public LatestProductAdapter(FragmentManager fragmentManager, ILatestProductPresenter latestProductPresenter, int type) {
        super(fragmentManager);
        ILatestProductPresenter = latestProductPresenter;
        mType = type;
    }
    @Override
    public Fragment getItem(int position) {
        return ILatestProductPresenter.getItem(position, mType);
    }

    @Override
    public int getCount() {
        return ILatestProductPresenter.getProductCount();
    }

    /*@Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }*/
}
