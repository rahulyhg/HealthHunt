package in.healthhunt.presenter.tagPresenter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import framework.retrofit.RestError;
import in.healthhunt.model.beans.Constants;
import in.healthhunt.model.preference.HealthHuntPreference;
import in.healthhunt.model.tags.TagItem;
import in.healthhunt.model.tags.TagRequest;
import in.healthhunt.presenter.interactor.tagInteractor.ITagInteractor;
import in.healthhunt.presenter.interactor.tagInteractor.TagInteractorImpl;
import in.healthhunt.view.tagView.ITagView;
import in.healthhunt.view.tagView.TagViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TagPresenterImp implements ITagPresenter, ITagInteractor.OnTagLoadFinishListener{

    private String TAG = TagPresenterImp.class.getSimpleName();
    private Context mContext;
    private List<TagItem> mTagList;
    private List<TagItem> mSelectedList;
    private ITagInteractor ITagInteractor;
    private int mPageNumber = 1;
    private final int MAX_TAG_PER_PAGE = 100;
    private ITagView ITagView;


    public TagPresenterImp(Context context, ITagView tagView) {
        mContext =  context;
        ITagView = tagView;
        ITagInteractor = new TagInteractorImpl();
        mTagList = new ArrayList<TagItem>();
        mSelectedList = new ArrayList<TagItem>();
    }

    @Override
    public void fetchTags() {
        ITagView.onShowProgress();
        mPageNumber = 1;
        TagRequest tagRequest = createTagRequest(mPageNumber);
        ITagInteractor.fetchAllTags(mContext, tagRequest, this);
    }

    public void fetchMoreTags(){
        ITagView.onShowProgress();
        mPageNumber++;
        TagRequest tagRequest = createTagRequest(mPageNumber);
        ITagInteractor.fetchAllTags(mContext, tagRequest, this);
    }

    @Override
    public int getTagCount() {
        int size = 0;
        if(mTagList != null){
            size = mTagList.size();
        }
        return size;
    }

    @Override
    public List<TagItem> getTagList() {
        return mTagList;
    }

    @Override
    public List<TagItem> getSelectedTagList() {
        return mSelectedList;
    }

    @Override
    public void addTag(TagItem tag) {
         if(mSelectedList == null) {
             mSelectedList = new ArrayList<TagItem>();
         }
        Log.d(TAG, "Select tag");
         if(!mSelectedList.contains(tag)) {
             mSelectedList.add(tag);
         }
    }

    @Override
    public void removeTag(TagItem tag) {
        if(mSelectedList == null){
            Log.d(TAG, "Selected list is empty");
            return;
        }

        int tagId = tag.getId();

        for(int i =0; i<mSelectedList.size(); i++) {
            TagItem tagItem = mSelectedList.get(i);
            if(tagItem.getId() == tagId){
                mSelectedList.remove(i);
                Log.d(TAG, "Unselect tag");
                break;
            }
        }
    }

    @Override
    public void selectAll() {
        mSelectedList.clear();
        mSelectedList.addAll(mTagList);
    }

    @Override
    public void unSelectAll() {
        mSelectedList.clear();
    }

    @Override
    public void storeSelectedTags() {
        Set<String> tags = new HashSet<String>();
        for(TagItem tagItem: mSelectedList){
            tags.add(String.valueOf(tagItem.getId()));
        }
        HealthHuntPreference.putSet(mContext, Constants.SELECTED_TAGS_KEY, tags);
    }

    @Override
    public TagItem getTag(int pos) {
        if(mTagList != null) {
            mTagList.get(pos);
        }
        return null;
    }

    @Override
    public TagViewHolder createTagViewHolder(View view) {
        return ITagView.createTagViewHolder(view);
    }

    @Override
    public void loadFragment(String fragmentName, Bundle bundle) {
        ITagView.loadFragment(fragmentName, bundle);
    }

    private TagRequest createTagRequest(int pageNumber) {
        TagRequest tagRequest = new TagRequest();
        tagRequest.setPage(pageNumber);
        tagRequest.setPerPage(MAX_TAG_PER_PAGE);
        return tagRequest;
    }

    @Override
    public void onSuccess() {
            ITagView.onHideProgress();
    }

    @Override
    public void onError(RestError errorInfo) {
        ITagView.onHideProgress();
        if(errorInfo != null) {
            ITagView.showAlert(errorInfo.getMessage());
        }
    }


    @Override
    public void setTags(List<TagItem> tags) {
        if(tags != null && !tags.isEmpty()) {
            mTagList.addAll(tags);
            ITagView.updateAdapter();
        }
        else {
            Log.i("TAG123", "Tag List is null");
        }
    }
}
