package in.healthhunt.presenter.interactor.articleInteractor;

import android.content.Context;

import java.util.List;
import java.util.Map;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IArticleInteractor {

    interface OnViewAllFinishListener {
        void onArticleSuccess(List<ArticlePostItem> items);
        void onError(RestError errorInfo);
    }

    interface OnFullViewFinishListener {
        void onArticleSuccess(ArticlePostItem item);
        void onError(RestError errorInfo);
    }

    interface OnArticleFinishListener {
        void onArticleSuccess(List<ArticlePostItem> items, int type);
        void onError(RestError errorInfo);
    }

    interface OnRelatedFinishListener {
        void onRelatedSuccess(List<ArticlePostItem> items);
        void onError(RestError errorInfo);
    }

    interface OnDeleteFinishListener {
        void onDeleteArticleSuccess(ArticlePostItem item);
        void onError(RestError errorInfo);
    }


    void fetchAllArticle(Context context, Map<String, String> queryMap, OnViewAllFinishListener finishListener);
    void fetchFullArticle(Context context, String id,  OnFullViewFinishListener finishListener);
    void fetchArticle(Context context, int type, Map<String, String> queryMap, OnArticleFinishListener articleFinishListener);
    void fetchRelatedArticle(Context context, Map<String, String> queryMap, OnRelatedFinishListener finishListener);
    void fetchArticlesCategory(Context context, int type, Map<String, String> queryMap, List<String> category, OnArticleFinishListener articleFinishListener);
    void fetchAllArticleCategory(Context context, Map<String, String> queryMap, List<String> category, OnViewAllFinishListener finishListener);
    void deleteArticle(Context context, String id, OnDeleteFinishListener deleteFinishListener);
}
