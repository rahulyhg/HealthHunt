package in.healthhunt.view.viewAll;

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
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.commonResponse.CurrentUser;
import in.healthhunt.presenter.viewAllPresenter.IViewAllPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ViewAllArticleHolder extends RecyclerView.ViewHolder{

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

    @BindView(R.id.view_all_pic)
    ImageView mAuthorImage;

    @BindView(R.id.view_all_name)
    TextView mAuthorName;

    @BindView(R.id.view_all_article_content)
    TextView mArticleTitle;

    @BindView(R.id.view_all_hash_tags)
    TextView mHashTags;

    @BindView(R.id.view_all_reading_time)
    TextView mReadingTime;

    @BindView(R.id.view_all_article_date)
    TextView mArticleDate;

    private IViewAllPresenter IViewAllPresenter;

    private ViewAllAdapter.ClickListener mClickListener;
    public ViewAllArticleHolder(View articleView, ViewAllAdapter.ClickListener clickListener, IViewAllPresenter viewAllPresenter) {
        super(articleView);
        ButterKnife.bind(this, articleView);
        mContext = articleView.getContext();
        mClickListener = clickListener;
        IViewAllPresenter = viewAllPresenter;
    }

    @OnClick(R.id.view_all_item_view)
    void onClick(View view) {
        if(mClickListener != null) {
            mClickListener.ItemClicked(view, getAdapterPosition());
        }
    }

    @OnClick(R.id.view_all_bookmark)
    void onBookMark(){
        ArticlePostItem postsItem = IViewAllPresenter.getArticle(getAdapterPosition());
        String id = String.valueOf(postsItem.getArticle_Id());
        CurrentUser currentUser = postsItem.getCurrent_user();
        if(currentUser != null) {
            if(!currentUser.isBookmarked()){
                IViewAllPresenter.bookmark(id);
            }
            else {
                IViewAllPresenter.unBookmark(id);
            }
        }
    }
}