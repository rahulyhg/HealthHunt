package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import in.healthhunt.view.homeScreenView.article.viewall.IViewAll;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ViewAllPresenterImp implements IViewAllPresenter, IViewAllInteractor.OnFinishListener {

    private String TAG = ViewAllPresenterImp.class.getSimpleName();
    private IViewAll IViewAll;
    private Context mContext;
    private IViewAllInteractor IViewAllInteractor;
    private List<ArticlePostItem> mArticlePosts;
    private List<ProductPostItem> mProductPosts;

    public ViewAllPresenterImp(Context context, IViewAll viewAll) {
        mContext =  context;
        IViewAll = viewAll;
        IViewAllInteractor = new ViewAllInteractorImpl();
    }

    @Override
    public int getCount(int type) {
        int count = 0;
        switch (type){
            case ArticleParams.BASED_ON_TAGS:
            case ArticleParams.LATEST_ARTICLES:
                if(mArticlePosts != null) {
                    count = mArticlePosts.size();
                }
                break;

            case ArticleParams.LATEST_PRODUCTS_ARTICLES:
                if(mProductPosts != null) {
                    count = mProductPosts.size();
                }
                break;
        }

        return count;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(View view) {
        return IViewAll.onCreateViewHolder(view);
    }

    @Override
    public void fetchAll(int type) {

        IViewAll.showProgress();
        Map<String, String> map = new HashMap<String, String>();

        switch (type) {
            case ArticleParams.BASED_ON_TAGS:
            case ArticleParams.LATEST_ARTICLES:
                Set<String> tagIds = HealthHuntPreference.getSet(mContext, Constants.SELECTED_TAGS_KEY);
                String tags = "";
                Iterator iterator = tagIds.iterator();
                while (iterator.hasNext()) {
                    tags = tags + iterator.next();
                    if(iterator.hasNext()){
                        tags = tags + ",";
                    }
                }
                map.put(ArticleParams.TAGS, tags);
                map.put(ArticleParams.OFFSET, String.valueOf(0));
                map.put(ArticleParams.LIMIT, String.valueOf(30));
                IViewAllInteractor.fetchAllArticle(mContext, map,this);
                break;

            case ArticleParams.LATEST_PRODUCTS_ARTICLES:
                map.put(ArticleParams.TYPE, ArticleParams.MARKET);
                map.put(ArticleParams.MARKT_TYPE, String.valueOf(1));
                map.put(ArticleParams.SECTION, ArticleParams.LATEST_BY_WEEK);
                map.put(ArticleParams.OFFSET, String.valueOf(0));
                map.put(ArticleParams.LIMIT, String.valueOf(30));
                IViewAllInteractor.fetchAllProduct(mContext, map, this);
                break;
        }
    }

    @Override
    public int getView() {
        return IViewAll.getViewLayout();
    }

    @Override
    public List<ArticlePostItem> getAllArticles() {
        return mArticlePosts;
    }

    @Override
    public List<ProductPostItem> getAllProduct() {
        return mProductPosts;
    }

    @Override
    public ArticlePostItem getArticle(int pos) {
        ArticlePostItem postItem = null;
        if(mArticlePosts != null && pos < mArticlePosts.size()){
            postItem =  mArticlePosts.get(pos);
        }
        return postItem;
    }

    @Override
    public ProductPostItem getProduct(int pos) {
        ProductPostItem postItem = null;
        if(mProductPosts != null && pos < mProductPosts.size()){
            postItem =  mProductPosts.get(pos);
        }
        return postItem;
    }


    @Override
    public void onArticleSuccess(List<ArticlePostItem> items) {
        IViewAll.hideProgress();
        mArticlePosts = items;
        IViewAll.updateAdapter();
    }

    @Override
    public void onProductSuccess(List<ProductPostItem> items) {
        IViewAll.hideProgress();
        mProductPosts = items;
        IViewAll.updateAdapter();
    }

    @Override
    public void onError(RestError errorInfo) {
        IViewAll.hideProgress();
    }
}
