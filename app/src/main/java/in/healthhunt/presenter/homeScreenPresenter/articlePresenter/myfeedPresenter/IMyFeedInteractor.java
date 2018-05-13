package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import android.content.Context;

import java.util.List;
import java.util.Map;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.articleResponse.PostsItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IMyFeedInteractor {

    interface OnArticleFinishListener {
        void onSuccess(List<PostsItem> items, int type);
        void onError(RestError errorInfo);
    }
    void fetchArticle(Context context, Map<String, String> queryMap, OnArticleFinishListener articleFinishListener);
    void fetchTrendingArticle(Context context, Map<String, String> queryMap, OnArticleFinishListener articleFinishListener);
    void fetchLatestArticle(Context context, Map<String, String> queryMap, OnArticleFinishListener articleFinishListener);
}
