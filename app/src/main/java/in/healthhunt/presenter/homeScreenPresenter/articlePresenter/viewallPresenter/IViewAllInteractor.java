package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter;

import android.content.Context;

import java.util.List;
import java.util.Map;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.productResponse.ProductPostItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IViewAllInteractor {

    interface OnFinishListener {
        void onArticleSuccess(List<ArticlePostItem> items);
        void onProductSuccess(List<ProductPostItem> items);
        void onError(RestError errorInfo);
    }
    void fetchAllArticle(Context context, Map<String, String> queryMap, OnFinishListener finishListener);
    void fetchAllProduct(Context context, Map<String, String> queryMap, OnFinishListener finishListener);
}
