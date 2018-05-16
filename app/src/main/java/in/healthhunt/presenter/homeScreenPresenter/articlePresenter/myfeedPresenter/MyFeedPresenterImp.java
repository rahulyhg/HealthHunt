package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

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
import in.healthhunt.presenter.preference.HealthHuntPreference;
import in.healthhunt.view.homeScreenView.article.myfeed.IMyFeedView;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class MyFeedPresenterImp implements IMyFeedPresenter, IMyFeedInteractor.OnArticleFinishListener, IMyFeedInteractor.OnProductFinishListener{

    private String TAG = MyFeedPresenterImp.class.getSimpleName();
    private IMyFeedView IMyFeedView;
    private Context mContext;
    private IMyFeedInteractor IMyFeedInteractor;
    private List<ArticlePostItem> mTagArticles;
    private List<ArticlePostItem> mTrendingArticles;
    private List<ArticlePostItem> mLatestArticles;
    private List<ArticlePostItem> mSponsoredArticles;
    private List<ProductPostItem> mTopProductArticles;
    private List<ProductPostItem> mLatestProductArticles;
    private Map<Integer, Integer> mArticlesType;
    private int fetchCount;


    public MyFeedPresenterImp(Context context, IMyFeedView feedView) {
        mContext =  context;
        IMyFeedView = feedView;
        IMyFeedInteractor = new MyFeedInteractorImpl();
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

        IMyFeedInteractor.fetchArticle(mContext, ArticleParams.BASED_ON_TAGS, map, this);
    }

    @Override
    public void fetchTrendingArticle(int offset, int limit) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.TRENDING, String.valueOf(1));
        map.put(ArticleParams.OFFSET, String.valueOf(offset));
        map.put(ArticleParams.LIMIT, String.valueOf(limit));
        IMyFeedInteractor.fetchArticle(mContext, ArticleParams.TRENDING_ARTICLES, map, this);
    }

    @Override
    public void fetchLatestArticle(int offset, int limit) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.SECTION, ArticleParams.LATEST_BY_WEEK);
        map.put(ArticleParams.OFFSET, String.valueOf(offset));
        map.put(ArticleParams.LIMIT, String.valueOf(limit));
        IMyFeedInteractor.fetchArticle(mContext, ArticleParams.LATEST_ARTICLES, map, this);
    }

    @Override
    public void fetchSponsoredArticle(String type, int offset, int limit) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.SPONSORED, type);
        map.put(ArticleParams.OFFSET, String.valueOf(offset));
        map.put(ArticleParams.LIMIT, String.valueOf(limit));
        IMyFeedInteractor.fetchArticle(mContext, ArticleParams.SPONSORED_ARTICLES, map, this);
    }

    @Override
    public void fetchTopProduct(int offset, int limit) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.TYPE, ArticleParams.MARKET);
        map.put(ArticleParams.MARKT_TYPE, String.valueOf(1));
        map.put(ArticleParams.OFFSET, String.valueOf(offset));
        map.put(ArticleParams.LIMIT, String.valueOf(limit));
        IMyFeedInteractor.fetchProduct(mContext, ArticleParams.TOP_PRODUCTS_ARTICLES, map, this);

    }

    @Override
    public void fetchLatestProduct(int offset, int limit) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(ArticleParams.TYPE, ArticleParams.MARKET);
        map.put(ArticleParams.MARKT_TYPE, String.valueOf(1));
        map.put(ArticleParams.OFFSET, String.valueOf(offset));
        map.put(ArticleParams.LIMIT, String.valueOf(limit));
        map.put(ArticleParams.SECTION, ArticleParams.LATEST_BY_WEEK);
        IMyFeedInteractor.fetchProduct(mContext, ArticleParams.LATEST_PRODUCTS_ARTICLES, map, this);

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
        return mTopProductArticles;
    }

    @Override
    public List<ProductPostItem> getLatestProductArticles() {
        return mLatestProductArticles;
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
                if(mTagArticles != null && !mTagArticles.isEmpty()){
                    addType(type);
                }
                break;

            case ArticleParams.TRENDING_ARTICLES:
                mTrendingArticles = items;
                fetchCount--;
                if(mTrendingArticles != null && !mTrendingArticles.isEmpty()){
                    addType(type);
                }
                break;

            case ArticleParams.LATEST_ARTICLES:
                mLatestArticles = items;
                fetchCount--;
                if(mLatestArticles != null && !mLatestArticles.isEmpty()){
                    addType(type);
                }
                break;

            case ArticleParams.SPONSORED_ARTICLES:
                mSponsoredArticles = items;
                fetchCount--;
                if(mSponsoredArticles != null && !mSponsoredArticles.isEmpty()){
                    addType(type);
                }
                break;
        }

        if(fetchCount == 0) {
            IMyFeedView.onLoadComplete();
            IMyFeedView.updateAdapter();
        }
    }

    @Override
    public void onProductSuccess(List<ProductPostItem> items, int type) {
        Log.i("TAGARTICLES" , "product type " + type);
        switch (type) {
            case ArticleParams.TOP_PRODUCTS_ARTICLES:
                mTopProductArticles = items;
                fetchCount--;
                if(mTopProductArticles != null && !mTopProductArticles.isEmpty()){
                    addType(type);
                }
                break;
            case ArticleParams.LATEST_PRODUCTS_ARTICLES:
                mLatestProductArticles = items;
                fetchCount--;
                if(mLatestProductArticles != null && !mLatestProductArticles.isEmpty()){
                    addType(type);
                }
                break;
        }

        if(fetchCount == 0) {
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

    private void addType(int type) {

        switch (type) {
            case ArticleParams.BASED_ON_TAGS:
                mArticlesType.put(0, type);
                break;

            case ArticleParams.TRENDING_ARTICLES:
                mArticlesType.put(1, type);
                break;

            case ArticleParams.SPONSORED_ARTICLES:
                mArticlesType.put(2, type);
                break;

            case ArticleParams.TOP_PRODUCTS_ARTICLES:
                mArticlesType.put(3, type);
                break;

            case ArticleParams.LATEST_ARTICLES:
                mArticlesType.put(4, type);
                break;

            case ArticleParams.LATEST_PRODUCTS_ARTICLES:
                mArticlesType.put(5, type);
                break;
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
