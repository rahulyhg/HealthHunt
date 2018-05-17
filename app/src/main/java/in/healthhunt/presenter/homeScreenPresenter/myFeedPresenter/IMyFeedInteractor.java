package in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter;

import android.content.Context;

import java.util.List;
import java.util.Map;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.productResponse.ProductPostItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IMyFeedInteractor {

    interface OnArticleFinishListener {
        void onArticleSuccess(List<ArticlePostItem> items, int type);
        void onError(RestError errorInfo);
    }

    interface OnProductFinishListener {
        void onProductSuccess(List<ProductPostItem> items, int type);
        void onError(RestError errorInfo);
    }

    void fetchArticle(Context context, int type, Map<String, String> queryMap, OnArticleFinishListener articleFinishListener);
    void fetchProduct(Context context, int type, Map<String, String> queryMap, OnProductFinishListener productFinishListener);
}
