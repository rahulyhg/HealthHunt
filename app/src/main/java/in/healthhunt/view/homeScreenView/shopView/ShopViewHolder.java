package in.healthhunt.view.homeScreenView.shopView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.presenter.homeScreenPresenter.shopPresenter.IShopPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ShopViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.shop_article_image)
    public ImageView mArticleImage;

    @BindView(R.id.shop_article_bookmark)
    public ImageView mBookMark;

    @BindView(R.id.shop_article_content)
    public TextView mShopContent;

    @BindView(R.id.shop_category_image)
    public ImageView mCategoryImage;

    @BindView(R.id.shop_category_name)
    TextView mCategoryView;

    @BindView(R.id.shop_article_item_view)
    LinearLayout mShopItemView;

    private int mViewHolderPos;

    private Context mContext;
    private IShopPresenter IShopPresenter;
    private ShopAdapter.ClickListener mClickListener;
    public ShopViewHolder(View itemView, IShopPresenter shopPresenter, ShopAdapter.ClickListener clickListener) {
        super(itemView);
        IShopPresenter = shopPresenter;
        mClickListener = clickListener;
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.shop_article_item_view)
    void onItemClick(View view) {
        if(mClickListener != null) {
            mClickListener.ItemClicked(view, getAdapterPosition());
        }
    }
}