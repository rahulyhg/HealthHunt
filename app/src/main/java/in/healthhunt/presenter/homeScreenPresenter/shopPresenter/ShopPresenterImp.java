package in.healthhunt.presenter.homeScreenPresenter.shopPresenter;

import android.content.Context;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.presenter.interactor.articleInteractor.ArticleInteractorImpl;
import in.healthhunt.presenter.interactor.articleInteractor.IArticleInteractor;
import in.healthhunt.view.homeScreenView.shopView.IShopView;
import in.healthhunt.view.homeScreenView.shopView.ShopViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ShopPresenterImp implements IShopPresenter, IArticleInteractor.OnViewAllFinishListener{

    private String TAG = ShopPresenterImp.class.getSimpleName();
    private IArticleInteractor IArticleInteractor;
    private Context mContext;
    private IShopView IShopView;
    private List<ArticlePostItem> mArticlePosts;

    public ShopPresenterImp(Context context, IShopView shopView) {
        mContext =  context;
        IArticleInteractor = new ArticleInteractorImpl();
        IShopView = shopView;
    }

    @Override
    public void onArticleSuccess(List<ArticlePostItem> items) {
        mArticlePosts = items;
        IShopView.hideProgress();
        IShopView.updateAdapter();
    }

    @Override
    public void onError(RestError errorInfo) {
        IShopView.hideProgress();
    }

    @Override
    public int getCount() {
        int count = 0;
        if(mArticlePosts != null){
            count = mArticlePosts.size();
        }
        return count;
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

    @Override
    public ShopViewHolder createViewHolder(View view) {
        return IShopView.createViewHolder(view);
    }

    @Override
    public void fetchMarkets() {
        IShopView.showProgress();
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.TYPE, ArticleParams.MARKET);
        map.put(ArticleParams.MARKT_TYPE, String.valueOf(ArticleParams.PRODUCT_SERVICES));
        map.put(ArticleParams.OFFSET, String.valueOf(0));
        map.put(ArticleParams.LIMIT, String.valueOf(30));
        IArticleInteractor.fetchAllArticle(mContext, map, this);
    }

    @Override
    public ArticlePostItem getArticle(int pos) {

        ArticlePostItem postItem = null;

        if(mArticlePosts != null){
            postItem = mArticlePosts.get(pos);
        }
        return postItem;
    }

    @Override
    public List<ArticlePostItem> getAllArticles() {
        return mArticlePosts;
    }
}
