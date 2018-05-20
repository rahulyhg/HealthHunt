package in.healthhunt.presenter.homeScreenPresenter;

import android.content.Context;

import framework.retrofit.RestError;
import in.healthhunt.model.articles.bookmarkResponse.BookMarkResponse;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface IInteractor {

    interface OnFinishListener {
        void onBookMarkSuccess(BookMarkResponse markResponse);
        void onError(RestError errorInfo);
    }

    void bookmark(Context context, String id, int type, OnFinishListener finishListener);
    void unBookmark(Context context, String id, int type, OnFinishListener finishListener);
   // void bookmark(Context context, int id, Map<String, String> queryMap, OnProductFinishListener productFinishListener);
}
