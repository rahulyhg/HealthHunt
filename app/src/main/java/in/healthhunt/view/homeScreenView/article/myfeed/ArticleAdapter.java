package in.healthhunt.view.homeScreenView.article.myfeed;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.IArticlePresenter;


/**
 * Created by abhishekkumar on 4/24/18.
 */

public class ArticleAdapter extends FragmentStatePagerAdapter {

    private IArticlePresenter IArticlePresenter;
    private int mType;

    public ArticleAdapter(FragmentManager fragmentManager, IArticlePresenter articlePresenter, int type) {
        super(fragmentManager);
        IArticlePresenter = articlePresenter;
        mType = type;
    }
    @Override
    public Fragment getItem(int position) {
        return IArticlePresenter.getItem(position, mType);
    }

    @Override
    public int getCount() {
        return IArticlePresenter.getCount();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
