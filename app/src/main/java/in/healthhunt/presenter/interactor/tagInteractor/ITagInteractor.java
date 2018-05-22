package in.healthhunt.presenter.interactor.tagInteractor;

import android.content.Context;

import java.util.List;

import framework.retrofit.RestError;
import in.healthhunt.model.tags.TagItem;
import in.healthhunt.model.tags.TagRequest;

/**
 * Created by abhishekkumar on 5/6/18.
 */

public interface ITagInteractor {

    interface OnTagLoadFinishListener {
        void onSuccess();
        void onError(RestError errorInfo);
        void setTags(List<TagItem> tags);

    }
    void fetchAllTags(Context context, TagRequest tagRequest, OnTagLoadFinishListener tagLoadFinishListener);
}
