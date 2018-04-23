package in.healthhunt.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.presenter.ITagPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class TagViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tag_image)
    public ImageView mTagImage;

    @BindView(R.id.tag)
    public TextView mTag;


    private ITagPresenter ITagPresenter;
    public TagViewHolder(View itemView, ITagPresenter tagPresenter) {
        super(itemView);
        ITagPresenter = tagPresenter;
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.tag_view)
    public void onTagClick() {
        //Based on the if condition add and remove method will called
    }


}