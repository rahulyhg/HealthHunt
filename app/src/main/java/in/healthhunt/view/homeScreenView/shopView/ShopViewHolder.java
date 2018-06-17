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
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.commonResponse.CurrentUser;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.presenter.homeScreenPresenter.shopPresenter.IShopPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ShopViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.shop_article_image)
    public ImageView mArticleImage;

    @BindView(R.id.shop_article_bookmark)
    public ImageView mBookMark;

   /* @BindView(R.id.shop_article_content)
    public TextView mShopContent;
*/
    /*@BindView(R.id.shop_category_image)
    public ImageView mCategoryImage;

    @BindView(R.id.shop_category_name)
    TextView mCategoryView;
*/
    @BindView(R.id.shop_article_item_view)
    LinearLayout mShopItemView;

    @BindView(R.id.shop_product_name)
    TextView mProductName;

    @BindView(R.id.shop_product_type)
    TextView mProductType;

    @BindView(R.id.shop_product_price)
    TextView mProductPrice;

    @BindView(R.id.shop_product_unit)
    TextView mProductUnit;


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

    @OnClick(R.id.shop_article_bookmark)
    void onBookMark(){
        ProductPostItem postsItem = IShopPresenter.getProduct(getAdapterPosition());
        String id = String.valueOf(postsItem.getProduct_id());
        CurrentUser currentUser = postsItem.getCurrent_user();
        if(currentUser != null) {
            if(!currentUser.isBookmarked()){
                IShopPresenter.bookmark(id, ArticleParams.PRODUCT);
            }
            else {
                IShopPresenter.unBookmark(id, ArticleParams.PRODUCT);
            }
        }
    }
}