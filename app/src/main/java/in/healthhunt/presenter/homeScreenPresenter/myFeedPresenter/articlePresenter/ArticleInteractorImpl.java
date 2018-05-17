package in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.articlePresenter;

import android.content.Context;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/16/18.
 */

public class ArticleInteractorImpl implements IArticleInteractor {

    @Override
    public void bookmark(Context context, final String id, final int type, final OnFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).bookmark(id, new ResponseResolver<String>() {
            @Override
            public void onSuccess(String s, Response response) {
                finishListener.onBookMarkSuccess(id, type);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }
}
