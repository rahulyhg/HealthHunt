package in.healthhunt.view.homeScreenView.article.viewall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ViewAllHolder extends RecyclerView.ViewHolder{

    private Context mContext;

    @BindView(R.id.view_all_item_view)
    RelativeLayout mItemView;

    @BindView(R.id.view_all_article_image)
    ImageView mArticleImage;

    @BindView(R.id.view_all_bookmark)
    ImageView mArticleBookMark;

    @BindView(R.id.view_all_category_icon)
    ImageView mCategoryImage;

    @BindView(R.id.view_all_category_name)
    TextView mCategoryName;

    @BindView(R.id.view_all_author_pic)
    ImageView mAuthorImage;

    @BindView(R.id.view_all_author_name)
    TextView mAuthorName;

    @BindView(R.id.view_all_article_content)
    TextView mArticleTitle;

    @BindView(R.id.view_all_hash_tags)
    TextView mHashTags;

    @BindView(R.id.view_all_reading_time)
    TextView mReadingTime;

    @BindView(R.id.view_all_article_date)
    TextView mArticleDate;

    private ViewAllAdapter.ClickListener mClickListener;
    public ViewAllHolder(View articleView, ViewAllAdapter.ClickListener clickListener) {
        super(articleView);
        ButterKnife.bind(this, articleView);
        mContext = articleView.getContext();
        mClickListener = clickListener;
    }

    @OnClick(R.id.view_all_item_view)
    void onClick(View view) {
        if(mClickListener != null) {
            mClickListener.ItemClicked(view, getAdapterPosition());
        }
    }
}