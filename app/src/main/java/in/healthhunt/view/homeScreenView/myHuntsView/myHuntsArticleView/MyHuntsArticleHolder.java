package in.healthhunt.view.homeScreenView.myHuntsView.myHuntsArticleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.presenter.homeScreenPresenter.myHuntPresenter.myHuntsArticlePresenter.IMyHuntsArticlePresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class MyHuntsArticleHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.my_hunts_article_image)
    public ImageView mArticleImage;

    @BindView(R.id.my_hunts_play_icon)
    public ImageView mPlayIcon;

    @BindView(R.id.my_hunts_article_bookmark)
    public ImageView mBookMark;

    @BindView(R.id.my_hunts_article_content)
    public TextView mShopContent;

    @BindView(R.id.my_hunts_category_image)
    public ImageView mCategoryImage;

    @BindView(R.id.my_hunts_category_name)
    TextView mCategoryView;



    private Context mContext;
    private IMyHuntsArticlePresenter IMyHuntsArticlePresenter;
    private MyHuntsArticleAdapter.ClickListener mClickListener;
    public MyHuntsArticleHolder(View itemView, IMyHuntsArticlePresenter myHuntsArticlePresenter, MyHuntsArticleAdapter.ClickListener clickListener) {
        super(itemView);
        IMyHuntsArticlePresenter = myHuntsArticlePresenter;
        mClickListener = clickListener;
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
        mPlayIcon.setVisibility(View.GONE);
    }

    @OnClick(R.id.my_hunts_article_item_view)
    void onItemClick(View view) {
        if(mClickListener != null) {
            mClickListener.ItemClicked(view, getAdapterPosition());
        }
    }


}