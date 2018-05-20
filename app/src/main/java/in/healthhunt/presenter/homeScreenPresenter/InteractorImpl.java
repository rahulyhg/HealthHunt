package in.healthhunt.presenter.homeScreenPresenter;

import android.content.Context;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.articles.bookmarkResponse.BookMarkResponse;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/16/18.
 */

public class InteractorImpl implements IInteractor {

    @Override
    public void bookmark(Context context, final String id, final int type, final OnFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).bookmark(id, new ResponseResolver<BookMarkResponse>() {
            @Override
            public void onSuccess(BookMarkResponse markResponse, Response response) {
                markResponse.getData().setType(type);
                markResponse.getData().setBookMark(true);
                finishListener.onBookMarkSuccess(markResponse);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }

    @Override
    public void unBookmark(Context context,final String id,final int type, final OnFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).unBookmark(id, new ResponseResolver<BookMarkResponse>() {
            @Override
            public void onSuccess(BookMarkResponse markResponse, Response response) {
                markResponse.getData().setType(type);
                markResponse.getData().setBookMark(false);
                finishListener.onBookMarkSuccess(markResponse);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }
}
