package in.healthhunt.presenter.tagPresenter;

import android.content.Context;
import android.util.Log;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.tags.TagRequest;
import in.healthhunt.model.tags.TagResponse;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/6/18.
 */

public class TagInteractorImpl implements ITagInteractor {
    @Override
    public void fetchAllTags(Context context, TagRequest tagRequest, final OnTagLoadFinishListener tagLoadFinishListener) {
        WebServicesWrapper.getInstance(context).fetchTags(tagRequest, new ResponseResolver<TagResponse>() {
            @Override
            public void onSuccess(TagResponse tagResponse, Response response) {
                tagLoadFinishListener.onSuccess();
                tagLoadFinishListener.setTags(tagResponse.getData().getTags());
                Log.i("TAG12345", "tagResponse = " +tagResponse);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                tagLoadFinishListener.onError(error);
            }
        });
    }
}
