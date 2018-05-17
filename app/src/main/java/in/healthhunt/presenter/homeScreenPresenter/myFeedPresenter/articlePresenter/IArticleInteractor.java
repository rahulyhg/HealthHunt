package in.healthhunt.presenter.homeScreenPresenter.myFeedPresenter.articlePresenter;

import android.content.Context;

import framework.retrofit.RestError;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IArticleInteractor {

    interface OnFinishListener {
        void onBookMarkSuccess(String id, int type);
        void onError(RestError errorInfo);
    }

    void bookmark(Context context, String id,  int type, OnFinishListener finishListener);
   // void bookmark(Context context, int id, Map<String, String> queryMap, OnProductFinishListener productFinishListener);
}
