package in.healthhunt.presenter.tagPresenter;

import android.view.View;

import java.util.List;

import in.healthhunt.model.tags.TagItem;
import in.healthhunt.view.tagView.TagViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface ITagPresenter {
    int getTagCount();
    List<TagItem> getTagList();
    List<TagItem> getSelectedTagList();
    void addTag(TagItem tag);
    void removeTag(TagItem tag);
    void selectAll();
    void unSelectAll();
    void storeSelectedTags();
    TagItem getTag(int pos);
    TagViewHolder createTagViewHolder(View view);
}
