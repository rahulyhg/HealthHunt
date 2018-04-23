package in.healthhunt.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import in.healthhunt.model.beans.Tag;
import in.healthhunt.view.TagViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TagPresenterImp implements ITagPresenter {

    private String TAG = TagPresenterImp.class.getSimpleName();
    private Context mContext;
    private List<Tag> mTagList;
    private List<Tag> mSelectedList;


    public TagPresenterImp(Context context) {
        mContext =  context;
    }


    @Override
    public void initTags(List<Tag> list) {
        mTagList = list;
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
    public List<Tag> getTagList() {
        return mTagList;
    }

    @Override
    public List<Tag> getSelectedTagList() {
        return mSelectedList;
    }

    @Override
    public void addTag(Tag tag) {
         if(mSelectedList == null) {
             mSelectedList = new ArrayList<Tag>();
         }
        Log.d(TAG, "Select tag");
         mSelectedList.add(tag);
    }

    @Override
    public void removeTag(long tagId) {
        if(mSelectedList == null){
            Log.d(TAG, "Selected list is empty");
            return;
        }

        for(int i =0; i<mSelectedList.size(); i++) {
            Tag tag = mSelectedList.get(i);
            if(tag.getmTagId() == tagId){
                mSelectedList.remove(i);
                Log.d(TAG, "Unselect tag");
                break;
            }
        }
    }

    @Override
    public TagViewHolder createTagViewHolder(View view, ITagPresenter tagPresenter) {
        return new TagViewHolder(view, tagPresenter);
    }


}
