package in.healthhunt.presenter.fullPresenter;

import android.content.Context;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.articles.postResponse.PostResponse;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/11/18.
 */

public class FullInteractorImpl implements IFullInteractor {

    @Override
    public void fetchFullArticle(Context context, String id, final OnFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).fetchFullArticle(id, new ResponseResolver<PostResponse>() {
            @Override
            public void onSuccess(PostResponse postResponse, Response response) {
                finishListener.onArticleSuccess(postResponse.getData().getPost());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }

    @Override
    public void fetchFullProduct(Context context, String id, OnFinishListener finishListener) {

    }
}
