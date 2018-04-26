package in.healthhunt.view;

import in.healthhunt.model.beans.Tag;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import in.healthhunt.R;
import in.healthhunt.presenter.ITagPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TagAdapter extends RecyclerView.Adapter<TagViewHolder> {

    private ITagPresenter ITagPresenter;
    private List<Tag> mTagList;

    public TagAdapter(ITagPresenter tagPresenter) {
        ITagPresenter = tagPresenter;
        mTagList = ITagPresenter.getTagList();
    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_item_view, parent, false);
        return ITagPresenter.createTagViewHolder(view, ITagPresenter);
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {
        Tag tag = mTagList.get(position);
        holder.mTag.setText(tag.getmTag());
        holder.updateTagBackground();
    }

    @Override
    public int getItemCount() {
        return ITagPresenter.getTagCount();
    }
}