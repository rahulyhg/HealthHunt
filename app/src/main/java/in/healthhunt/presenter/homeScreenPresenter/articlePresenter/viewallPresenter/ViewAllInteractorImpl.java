package in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter;

import android.content.Context;

import java.util.Map;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticleResponse;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/11/18.
 */

public class ViewAllInteractorImpl implements IViewAllInteractor {
    @Override
    public void fetchAllArticle(Context context, Map<String, String> queryMap, final OnArticleFinishListener articleFinishListener) {
        WebServicesWrapper.getInstance(context).fetchArticles(queryMap, new ResponseResolver<ArticleResponse>() {
            @Override
            public void onSuccess(ArticleResponse response, Response response2) {
                articleFinishListener.onSuccess(response.getData().getPosts(), ArticleParams.BASED_ON_TAGS);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                articleFinishListener.onError(error);
            }
        });
    }
}
