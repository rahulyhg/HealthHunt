package in.healthhunt.presenter.interactor.searchInteractor;

import android.content.Context;

import java.util.List;
import java.util.Map;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface ISearchInteractor {

    interface OnFinishListener {
        void onSearchSuccess(List<ArticlePostItem> postItems);
        void onError(RestError errorInfo);
    }

    void searchArticles(Context context, Map<String, String> queryMap, OnFinishListener finishListener);
}
