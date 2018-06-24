package in.healthhunt.presenter.interactor.searchInteractor;

import android.content.Context;

import java.util.Map;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.articles.articleResponse.ArticleData;
import in.healthhunt.model.response.HHResponse;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/16/18.
 */

public class SearchInteractorImpl implements ISearchInteractor {

    @Override
    public void searchArticles(Context context, Map<String, String> queryMap, final OnFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).searchArticles(queryMap, new ResponseResolver<HHResponse<ArticleData>>() {
            @Override
            public void onSuccess(HHResponse<ArticleData> articleData, Response response) {
                finishListener.onSearchSuccess(articleData.getData().getPosts());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }
}
