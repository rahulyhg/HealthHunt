package in.healthhunt.presenter.interactor.tagInteractor;

import android.content.Context;
import android.util.Log;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.response.HHResponse;
import in.healthhunt.model.tags.TagData;
import in.healthhunt.model.tags.TagRequest;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/6/18.
 */

public class TagInteractorImpl implements ITagInteractor {
    @Override
    public void fetchAllTags(Context context, TagRequest tagRequest, final OnTagLoadFinishListener tagLoadFinishListener) {
        WebServicesWrapper.getInstance(context).fetchTags(tagRequest, new ResponseResolver<HHResponse<TagData>>() {
            @Override
            public void onSuccess(HHResponse<TagData> tagData, Response response) {
                tagLoadFinishListener.onSuccess();
                tagLoadFinishListener.setTags(tagData.getData().getTags());
                Log.i("TAG12345", "tagResponse = " +tagData);
            }

            @Override
            public void onFailure(RestError error, String msg) {
                tagLoadFinishListener.onError(error);
            }
        });
    }
}
