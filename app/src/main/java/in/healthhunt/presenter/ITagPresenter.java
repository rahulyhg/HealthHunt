package in.healthhunt.presenter;

import android.view.View;

import java.util.List;

import in.healthhunt.model.beans.Tag;
import in.healthhunt.view.TagViewHolder;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public interface ITagPresenter {
    void initTags(List<Tag> list);
    int getTagCount();
    List<Tag> getTagList();
    List<Tag> getSelectedTagList();
    void addTag(Tag tag);
    void removeTag(long tagId);
    TagViewHolder createTagViewHolder(View view, ITagPresenter tagPresenter);
}
