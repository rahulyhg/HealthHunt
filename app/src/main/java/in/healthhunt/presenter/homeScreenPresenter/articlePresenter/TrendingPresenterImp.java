package in.healthhunt.presenter.homeScreenPresenter.articlePresenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import in.healthhunt.presenter.homeScreenPresenter.IHomePresenter;
import in.healthhunt.view.homeScreenView.IHomeView;
import in.healthhunt.view.homeScreenView.article.ArticleViewHolder;
import in.healthhunt.view.homeScreenView.article.ContinueArticleViewHolder;
import in.healthhunt.view.homeScreenView.article.ITrendingView;
import in.healthhunt.view.homeScreenView.article.TrendingArticleViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TrendingPresenterImp implements ITrendingPresenter {

    private String TAG = TrendingPresenterImp.class.getSimpleName();
    private ITrendingView ITrendingView;
    private Context mContext;

    public TrendingPresenterImp(Context context, ITrendingView trendingView) {
        mContext =  context;
        ITrendingView = trendingView;
    }

    @Override
    public int getCount() {
        return ITrendingView.getCount();
    }

    @Override
    public RecyclerView.ViewHolder createArticleHolder(View view) {
        return new TrendingArticleViewHolder(view);
    }
}
