package in.healthhunt.presenter.homeScreenPresenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;

import in.healthhunt.view.homeScreenView.article.ArticleViewHolder;
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
    public int getCount() {
        return IHomeView.getCount();
    }

    @Override
    public ArticleViewHolder createArticleHolder(View view, FragmentManager fragmentManager) {
        return new ArticleViewHolder(view, fragmentManager);
    }
}
