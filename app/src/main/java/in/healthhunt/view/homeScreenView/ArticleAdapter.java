package in.healthhunt.view.homeScreenView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import in.healthhunt.presenter.homeScreenPresenter.IArticlePresenter;


/**
 * Created by abhishekkumar on 4/24/18.
 */

public class ArticleAdapter extends FragmentPagerAdapter {

    private IArticlePresenter IArticlePresenter;

    public ArticleAdapter(FragmentManager fragmentManager, IArticlePresenter articlePresenter) {
        super(fragmentManager);
        IArticlePresenter = articlePresenter;
    }
    @Override
    public Fragment getItem(int position) {
        return IArticlePresenter.getItem(position);
    }

    @Override
    public int getCount() {
        return IArticlePresenter.getCount();
    }
}
