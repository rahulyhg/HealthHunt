package in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.model.preference.HealthHuntPreference;
import in.healthhunt.presenter.interactor.articleInteractor.ArticleInteractorImpl;
import in.healthhunt.presenter.interactor.articleInteractor.IArticleInteractor;
import in.healthhunt.presenter.interactor.productInteractor.IProductInteractor;
import in.healthhunt.presenter.interactor.productInteractor.ProductInteractorImpl;
import in.healthhunt.view.homeScreenView.myFeedView.IMyFeedView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class MyFeedPresenterImp implements IMyFeedPresenter, IArticleInteractor.OnArticleFinishListener, IProductInteractor.OnProductFinishListener{

    private String TAG = MyFeedPresenterImp.class.getSimpleName();
    private IMyFeedView IMyFeedView;
    private Context mContext;
    //private IMyFeedInteractor IMyFeedInteractor;
    private List<ArticlePostItem> mTagArticles;
    private List<ArticlePostItem> mTrendingArticles;
    private List<ArticlePostItem> mLatestArticles;
    private List<ArticlePostItem> mSponsoredArticles;
    private List<ProductPostItem> mTopProduct;
    private List<ProductPostItem> mLatestProduct;
    private Map<Integer, Integer> mArticlesType;

    private IArticleInteractor IArticleInteractor;
    private IProductInteractor IProductInteractor;

    private int fetchCount;


    public MyFeedPresenterImp(Context context, IMyFeedView feedView) {
        mContext =  context;
        IMyFeedView = feedView;
        //IMyFeedInteractor = new MyFeedInteractorImpl();
        IArticleInteractor = new ArticleInteractorImpl();
        IProductInteractor = new ProductInteractorImpl();
        mArticlesType = new HashMap<Integer, Integer>();
    }

    @Override
    public int getCount() {
        return IMyFeedView.getCount();
    }

    @Override
    public RecyclerView.ViewHolder createArticleHolder(View view, int type) {
        return IMyFeedView.createArticleHolder(view, type);
    }

    @Override
    public void fetchTagsArticle(int offset, int limit) {

        Set<String> tagIds = HealthHuntPreference.getSet(mContext, Constants.SELECTED_TAGS_KEY);
        String tags = "";
        Iterator iterator = tagIds.iterator();
        while (iterator.hasNext()) {
            tags = tags + iterator.next();
            if(iterator.hasNext()){
                tags = tags + ",";
            }
        }
        Log.i("TAG1111", "ids = " + tags);

        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.TAGS, tags);
        map.put(ArticleParams.OFFSET, String.valueOf(offset));
        map.put(ArticleParams.LIMIT, String.valueOf(limit));

        IArticleInteractor.fetchArticle(mContext, ArticleParams.BASED_ON_TAGS, map, this);
    }

    @Override
    public void fetchTrendingArticle(int offset, int limit) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.TRENDING, String.valueOf(1));
        map.put(ArticleParams.OFFSET, String.valueOf(offset));
        map.put(ArticleParams.LIMIT, String.valueOf(limit));
        IArticleInteractor.fetchArticle(mContext, ArticleParams.TRENDING_ARTICLES, map, this);
    }

    @Override
    public void fetchLatestArticle(int offset, int limit) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.SECTION, ArticleParams.LATEST_BY_MONTH);
        map.put(ArticleParams.OFFSET, String.valueOf(offset));
        map.put(ArticleParams.LIMIT, String.valueOf(limit));
        IArticleInteractor.fetchArticle(mContext, ArticleParams.LATEST_ARTICLES, map, this);
    }

    @Override
    public void fetchSponsoredArticle(String type, int offset, int limit) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.SPONSORED, type);
        map.put(ArticleParams.OFFSET, String.valueOf(offset));
        map.put(ArticleParams.LIMIT, String.valueOf(limit));
        IArticleInteractor.fetchArticle(mContext, ArticleParams.SPONSORED_ARTICLES, map, this);
    }

    @Override
    public void fetchTopProduct(int offset, int limit) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.TYPE, ArticleParams.MARKET);
        map.put(ArticleParams.MARKT_TYPE, String.valueOf(1));
        map.put(ArticleParams.OFFSET, String.valueOf(offset));
        map.put(ArticleParams.LIMIT, String.valueOf(limit));
        IProductInteractor.fetchProduct(mContext, ArticleParams.TOP_PRODUCTS, map, this);

    }

    @Override
    public void fetchLatestProduct(int offset, int limit) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.TYPE, ArticleParams.MARKET);
        map.put(ArticleParams.MARKT_TYPE, String.valueOf(1));
        map.put(ArticleParams.OFFSET, String.valueOf(offset));
        map.put(ArticleParams.LIMIT, String.valueOf(limit));
        map.put(ArticleParams.SECTION, ArticleParams.LATEST_BY_WEEK);
        IProductInteractor.fetchProduct(mContext, ArticleParams.LATEST_PRODUCTS, map, this);

    }

    @Override
    public List<ArticlePostItem> getTagArticles() {
        return mTagArticles;
    }

    @Override
    public List<ArticlePostItem> getTrendingArticles() {
        return mTrendingArticles;
    }

    @Override
    public List<ArticlePostItem> getLatestArticles() {
        return mLatestArticles;
    }

    @Override
    public List<ArticlePostItem> getSponsoredArticles() {
        return mSponsoredArticles;
    }

    @Override
    public List<ProductPostItem> getTopProductArticles() {
        return mTopProduct;
    }

    @Override
    public List<ProductPostItem> getLatestProductArticles() {
        return mLatestProduct;
    }

    @Override
    public Map<Integer, Integer> getArticlesType() {
        return mArticlesType;
    }

    @Override
    public void fetchData() {
        fetchCount = 6;
        fetchTagsArticle(0, 5);
        fetchTrendingArticle(0, 2);
        fetchLatestArticle(0, 5);
        fetchSponsoredArticle(ArticleParams.POST_FORMAT_IMAGE, 0, 2);
        fetchTopProduct(0, 2);
        fetchLatestProduct(0, 5);
    }

    @Override
    public int getView(int type) {
        return IMyFeedView.getView(type);
    }

    @Override
    public void deleteArticleType(int pos) {
        if(mArticlesType != null && pos < mArticlesType.size()) {
            updateMapWithRemoved(pos);
        }
    }

    @Override
    public void addContinueArticles() {
        updateMapWithAdded(1, ArticleParams.CONTINUE_ARTICLES);
    }

    @Override
    public void onArticleSuccess(List<ArticlePostItem> items, int type) {
        Log.i("TAGARTICLES" , "article type " + type);
        switch (type) {
            case ArticleParams.BASED_ON_TAGS:
                mTagArticles = items;
                fetchCount--;
                break;

            case ArticleParams.TRENDING_ARTICLES:
                mTrendingArticles = items;
                fetchCount--;
                break;

            case ArticleParams.LATEST_ARTICLES:
                mLatestArticles = items;
                fetchCount--;
                break;

            case ArticleParams.SPONSORED_ARTICLES:
                mSponsoredArticles = items;
                fetchCount--;
                break;
        }

        if(fetchCount == 0) {
            buildMap();
            IMyFeedView.onLoadComplete();
            IMyFeedView.updateAdapter();
        }
    }

    @Override
    public void onProductSuccess(List<ProductPostItem> items, int type) {
        Log.i("TAGARTICLES" , "product type " + type);
        switch (type) {
            case ArticleParams.TOP_PRODUCTS:
                mTopProduct = items;
                fetchCount--;
                break;
            case ArticleParams.LATEST_PRODUCTS:
                mLatestProduct = items;
                fetchCount--;
                break;
        }

        if(fetchCount == 0) {
            buildMap();
            IMyFeedView.onLoadComplete();
            IMyFeedView.updateAdapter();
        }
    }

    @Override
    public void onError(RestError errorInfo) {
        String msg = "Server error";
        if(errorInfo != null && errorInfo.getMessage() != null) {
            msg = errorInfo.getMessage();
        }
        IMyFeedView.showAlert(msg);
    }

    private void buildMap() {

        int index = 0;

        if(mTagArticles != null && !mTagArticles.isEmpty()){
            mArticlesType.put(index, ArticleParams.BASED_ON_TAGS);
            index++;
        }

        if(mTrendingArticles != null && !mTrendingArticles.isEmpty()){
            mArticlesType.put(index, ArticleParams.TRENDING_ARTICLES);
            index++;
        }

        if(mSponsoredArticles != null && !mSponsoredArticles.isEmpty()){
            mArticlesType.put(index, ArticleParams.SPONSORED_ARTICLES);
            index++;
        }

        if(mTopProduct != null && !mTopProduct.isEmpty()){
            mArticlesType.put(index, ArticleParams.TOP_PRODUCTS);
            index++;
        }

        if(mLatestArticles != null && !mLatestArticles.isEmpty()){
            mArticlesType.put(index, ArticleParams.LATEST_ARTICLES);
            index++;
        }

        if(mLatestProduct != null && !mLatestProduct.isEmpty()){
            mArticlesType.put(index, ArticleParams.LATEST_PRODUCTS);
            index++;
        }
    }

    private void updateMapWithRemoved(int pos) {
        for (int i = pos; i < mArticlesType.size() - 1; i++) {
            int type = mArticlesType.get(i + 1);
            mArticlesType.put(i, type);
        }
        mArticlesType.remove(mArticlesType.size()-1);
    }

    private void updateMapWithAdded(int pos, int articleType) {
        for (int i = pos; i < mArticlesType.size(); i++) {
            int type = mArticlesType.get(i);
            mArticlesType.put(i+1, type);
        }
        mArticlesType.put(pos,articleType);
    }
}
