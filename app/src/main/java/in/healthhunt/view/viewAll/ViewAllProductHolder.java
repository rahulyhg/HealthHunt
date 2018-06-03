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
import in.healthhunt.model.articles.commonResponse.CurrentUser;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.presenter.viewAllPresenter.IViewAllPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ViewAllProductHolder extends RecyclerView.ViewHolder{

    private Context mContext;

    @BindView(R.id.view_all_product_item_view)
    RelativeLayout mTagItemView;

    @BindView(R.id.view_all_product_image)
    ImageView mProductImage;

    @BindView(R.id.view_all_product_bookmark)
    ImageView mProductBookMark;

    @BindView(R.id.view_all_product_name)
    TextView mProductName;

    @BindView(R.id.view_all_product_price)
    TextView mProductPrice;

    @BindView(R.id.view_all_product_unit)
    TextView mProductUnit;

    private IViewAllPresenter IViewAllPresenter;

    private ViewAllAdapter.ClickListener mClickListener;
    public ViewAllProductHolder(View articleView, ViewAllAdapter.ClickListener clickListener, IViewAllPresenter viewAllPresenter) {
        super(articleView);
        ButterKnife.bind(this, articleView);
        mContext = articleView.getContext();
        mClickListener = clickListener;
        IViewAllPresenter = viewAllPresenter;
    }

    @OnClick(R.id.view_all_product_item_view)
    void onClick(View view) {
        if(mClickListener != null) {
            mClickListener.ItemClicked(view, getAdapterPosition());
        }
    }

    @OnClick(R.id.view_all_product_bookmark)
    void onBookMark(){
        ProductPostItem postsItem = IViewAllPresenter.getProduct(getAdapterPosition());
        String id = String.valueOf(postsItem.getProduct_id());
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