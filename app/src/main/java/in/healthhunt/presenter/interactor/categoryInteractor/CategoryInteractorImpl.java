package in.healthhunt.presenter.interactor.categoryInteractor;

import android.content.Context;

import java.util.Map;

import framework.retrofit.ResponseResolver;
import framework.retrofit.RestError;
import framework.retrofit.WebServicesWrapper;
import in.healthhunt.model.response.HHResponse;
import in.healthhunt.model.tags.TagData;
import retrofit2.Response;

/**
 * Created by abhishekkumar on 5/6/18.
 */

public class CategoryInteractorImpl implements ICategoryInteractor {

    @Override
    public void fetchCategories(Context context, Map<String, String> map, final OnFinishListener finishListener) {
        WebServicesWrapper.getInstance(context).fetchCategories(map, new ResponseResolver<HHResponse<TagData>>() {
            @Override
            public void onSuccess(HHResponse<TagData> tagDataHHResponse, Response response) {
                finishListener.onCategorySuccess(tagDataHHResponse.getData().getTags());
            }

            @Override
            public void onFailure(RestError error, String msg) {
                finishListener.onError(error);
            }
        });
    }
}
