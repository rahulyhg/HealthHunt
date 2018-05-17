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

    private ViewAllAdapter.ClickListener mClickListener;
    public ViewAllProductHolder(View articleView, ViewAllAdapter.ClickListener clickListener) {
        super(articleView);
        ButterKnife.bind(this, articleView);
        mContext = articleView.getContext();
        mClickListener = clickListener;
    }

    @OnClick(R.id.view_all_product_item_view)
    void onClick(View view) {
        if(mClickListener != null) {
            mClickListener.ItemClicked(view, getAdapterPosition());
        }
    }
}