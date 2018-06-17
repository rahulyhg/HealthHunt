package in.healthhunt.presenter.interactor.articleInteractor;

import android.content.Context;

import java.util.List;
import java.util.Map;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.articles.articleResponse.ArticleData;
import in.healthhunt.model.articles.fullArticleResponse.FullArticleResponse;
import in.healthhunt.model.response.HHResponse;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/11/18.
 */

public class ArticleInteractorImpl implements IArticleInteractor {
    @Override
    public void fetchAllArticle(Context context, Map<String, String> queryMap, final OnViewAllFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).fetchArticles(queryMap, new ResponseResolver<HHResponse<ArticleData>>() {
            @Override
            public void onSuccess(HHResponse<ArticleData> articleDat, Response response2) {
                finishListener.onArticleSuccess(articleDat.getData().getPosts());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }

    @Override
    public void fetchAllArticleCategory(Context context, Map<String, String> queryMap, List<String> category, final OnViewAllFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).fetchArticlesCategory(queryMap, category,  new ResponseResolver<HHResponse<ArticleData>>() {
            @Override
            public void onSuccess(HHResponse<ArticleData> articleDat, Response response2) {
                finishListener.onArticleSuccess(articleDat.getData().getPosts());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }

    @Override
    public void fetchFullArticle(Context context, String id, final OnFullViewFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).fetchFullArticle(id, new ResponseResolver<FullArticleResponse>() {
            @Override
            public void onSuccess(FullArticleResponse postResponse, Response response) {
                finishListener.onArticleSuccess(postResponse.getData().getPost());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }

    @Override
    public void fetchArticle(Context context, final int type, Map<String, String> queryMap, final OnArticleFinishListener articleFinishListener) {
        WebServicesWrapper.getInstance(context).fetchArticles(queryMap, new ResponseResolver<HHResponse<ArticleData>>() {
            @Override
            public void onSuccess(HHResponse<ArticleData> articleData, Response response) {
                articleFinishListener.onArticleSuccess(articleData.getData().getPosts(), type);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                articleFinishListener.onError(error);
            }
        });
    }

    @Override
    public void fetchArticlesCategory(Context context, final int type, Map<String, String> queryMap, List<String> category, final OnArticleFinishListener articleFinishListener) {
        WebServicesWrapper.getInstance(context).fetchArticlesCategory(queryMap, category, new ResponseResolver<HHResponse<ArticleData>>() {
            @Override
            public void onSuccess(HHResponse<ArticleData> articleData, Response response) {
                articleFinishListener.onArticleSuccess(articleData.getData().getPosts(), type);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                articleFinishListener.onError(error);
            }
        });
    }

    @Override
    public void fetchRelatedArticle(Context context, Map<String, String> queryMap, final OnRelatedFinishListener articleFinishListener) {
        WebServicesWrapper.getInstance(context).fetchArticles(queryMap, new ResponseResolver<HHResponse<ArticleData>>() {
            @Override
            public void onSuccess(HHResponse<ArticleData> articleDat, Response response2) {
                articleFinishListener.onRelatedSuccess(articleDat.getData().getPosts());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                articleFinishListener.onError(error);
            }
        });
    }

}
