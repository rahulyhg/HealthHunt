package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import android.content.Context;

import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.view.homeScreenView.article.myfeed.ITrendingSponsoredView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TrendingSponsoredPresenterImp implements ITrendingSponsoredPresenter {

    private String TAG = TrendingSponsoredPresenterImp.class.getSimpleName();
    private ITrendingSponsoredView ITrendingSponsoredView;
    private Context mContext;

    public TrendingSponsoredPresenterImp(Context context, ITrendingSponsoredView trendingSponsoredView) {
        mContext =  context;
        ITrendingSponsoredView = trendingSponsoredView;
    }

    @Override
    public int getCount() {
        return ITrendingSponsoredView.getArticleCount();
    }

    @Override
    public ArticlePostItem getTrendingArticles(int pos) {
        return ITrendingSponsoredView.getTrendingArticle(pos);
    }

    @Override
    public ArticlePostItem getSponsoredArticles(int pos) {
        return ITrendingSponsoredView.getSponsoredArticle(pos);
    }

}
