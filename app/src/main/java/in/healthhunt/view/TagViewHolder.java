package in.healthhunt.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    @BindView(R.id.tag_view)
    LinearLayout mTagView;

    boolean isClicked;

    private Context mContext;
    private ITagPresenter ITagPresenter;
    public TagViewHolder(View itemView, ITagPresenter tagPresenter) {
        super(itemView);
        ITagPresenter = tagPresenter;
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.tag_view)
    public void onTagClick() {
        //isClicked = !isClicked;
        //updateTagBackground();
        //Based on the if condition add and remove method will called

    }

    public void updateTagBackground(){
//        if(isClicked){
//            mTagView.setBackgroundColor(mContext.getResources().getColor(R.color.hh_green_light2));
//            mTag.setTextColor(Color.WHITE);
//        }
//        else {
//            mTagView.setBackgroundColor(mContext.getResources().getColor(R.color.hh_item_bg_color));
//            mTag.setTextColor(mContext.getResources().getColor(R.color.hh_green_light2));
//        }
    }


}