package in.healthhunt.presenter.homeScreenPresenter.articlePresenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import in.healthhunt.model.beans.Constants;
import in.healthhunt.view.homeScreenView.article.IArticleView;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class ArticlePresenterImp implements IArticlePresenter {

    private IArticleView IArticleView;
    private Context mContext;
    public ArticlePresenterImp(Context context, IArticleView articleView) {
        mContext = context;
        IArticleView = articleView;
    }


    @Override
    public void loadFragment(String tag, Bundle bundle) {
        IArticleView.showFragment(tag, bundle);
    }

    @Override
    public int getCount() {
        return IArticleView.getArticleCount();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = IArticleView.getArticleItem(position);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.VIEWPAGER_FRAGMENT_NO_KEY, position);
        fragment.setArguments(bundle);
        return fragment;
    }
}
