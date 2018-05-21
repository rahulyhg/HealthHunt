package in.healthhunt.presenter.homeScreenPresenter.myHuntPresenter;

import android.content.Context;

import java.util.Map;

import framework.retrofit.RestError;

/**
 * Created by abhishekkumar on 5/6/18.
 */

public interface IMyHuntsInteractor{

    interface OnFinishListener {
        void onSuccess();
        void onError(RestError errorInfo);
    }
    void fetchArticles(Context context, Map<String, String> queryMap, OnFinishListener tagLoadFinishListener);
}
