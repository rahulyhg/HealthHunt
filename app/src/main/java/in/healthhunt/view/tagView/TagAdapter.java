package in.healthhunt.view.tagView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import in.healthhunt.R;
import in.healthhunt.model.tags.TagItem;
import in.healthhunt.presenter.tagPresenter.ITagPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TagAdapter extends RecyclerView.Adapter<TagViewHolder> {

    private ITagPresenter ITagPresenter;
    private Context mContext;
    private boolean isSelectAll;

    public TagAdapter(Context context, ITagPresenter tagPresenter) {
        mContext = context;
        ITagPresenter = tagPresenter;
    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("TAG1223", "tags listdd " + ITagPresenter.getTagCount());
        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item_view, parent, false);
        return ITagPresenter.createTagViewHolder(view, ITagPresenter);
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {
        Log.i("TAGPress", "Pressed");
        List<TagItem> tags = ITagPresenter.getTagList();
        TagItem tagItem = tags.get(position);
        holder.mTag.setText(tagItem.getName());
        holder.setViewPos(position);
        holder.updateTag(tagItem);

        String url = tagItem.getIcon();
        if(url != null) {
            Glide.with(mContext)
                    .load(url)
                    .placeholder(R.mipmap.ic_tag_item_icon)
                    .into(holder.mTagImage);
        }
    }

    @Override
    public int getItemCount() {
        return ITagPresenter.getTagCount();
    }


    public void setSelectAll(boolean isAll) {
        List<TagItem> list = ITagPresenter.getTagList();
        for(TagItem tagItem: list) {
            tagItem.setPressed(isAll);
        }
        notifyDataSetChanged();
    }
}