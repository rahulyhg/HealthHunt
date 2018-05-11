package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter;

import android.content.Context;
import android.util.Log;

import java.util.Map;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.articles.articleResponse.ArticleResponse;
import in.healthhunt.model.articles.articleResponse.MediaItem;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/10/18.
 */

public class MyFeedInteractorImpl implements IMyFeedInteractor {
    @Override
    public void fetchArticle(Context context, Map<String, String> queryMap, final OnArticleFinishListener articleFinishListener) {
        WebServicesWrapper.getInstance(context).fetchArticles(queryMap, new ResponseResolver<ArticleResponse>() {
            @Override
            public void onSuccess(ArticleResponse articleResponse, Response response) {
                MediaItem media = articleResponse.getData().getPosts().get(0).getMedia().get(0);
                Log.i("TAGMEDIA", "Media Item = " + media);
                    articleFinishListener.onSuccess(articleResponse.getData().getPosts(), 0);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                    articleFinishListener.onError(error);
            }
        });
    }

    @Override
    public void fetchTrendingArticle(Context context, Map<String, String> queryMap, final OnArticleFinishListener articleFinishListener) {
        WebServicesWrapper.getInstance(context).fetchArticles(queryMap, new ResponseResolver<ArticleResponse>() {
            @Override
            public void onSuccess(ArticleResponse articleResponse, Response response) {
                articleFinishListener.onSuccess(articleResponse.getData().getPosts(), 1);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                articleFinishListener.onError(error);
            }
        });
    }
}
