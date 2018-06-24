package in.healthhunt.presenter.interactor.categoryInteractor;

import android.content.Context;

import java.util.List;
import java.util.Map;

import framework.retrofit.RestError;
import in.healthhunt.model.tags.TagItem;

/**
 * Created by abhishekkumar on 5/6/18.
 */

public interface ICategoryInteractor {

    interface OnFinishListener {
        void onCategorySuccess(List<TagItem> categoriesList);
        void onError(RestError errorInfo);
    }
    void fetchCategories(Context context, Map<String, String> map, OnFinishListener finishListener);
}
