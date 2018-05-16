package in.healthhunt.view.homeScreenView.article.myfeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.healthhunt.R;
import in.healthhunt.model.articles.commonResponse.MediaItem;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.myfeedPresenter.ITopProductPresenter;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class TopProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ITopProductPresenter ITopProductPresenter;
    private Context mContext;
    private ClickListener mClickListener;


    public TopProductAdapter(Context context, ITopProductPresenter topProductPresenter) {
        mContext = context;
        ITopProductPresenter = topProductPresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("TAg@@","viewType");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_products_article_item_view, parent, false);
        return new TopProductItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setContent(((TopProductItemViewHolder)holder), position);
    }

    @Override
    public int getItemCount() {
        return ITopProductPresenter.getProductCount();
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public interface ClickListener {
        void ItemClicked(View v, int position);
    }

    private void setContent(TopProductItemViewHolder holder, int pos) {

        ProductPostItem postsItem = ITopProductPresenter.getTopProduct(pos);
        if(postsItem != null) {
            String url = null;
            List<MediaItem> mediaItems = postsItem.getMedia();
            if(mediaItems != null && !mediaItems.isEmpty()) {
                MediaItem media = mediaItems.get(0);
                if("image".equals(media.getMedia_type())) {
                    url = media.getUrl();

                }
            }
            if(url != null) {
                Log.i("TAG11", "url " + url);
                Glide.with(mContext).load(url).placeholder(R.drawable.artical).into(holder.mProductImage);
            }
            else {
                holder.mProductImage.setBackgroundResource(R.drawable.artical);
            }


            String productName = postsItem.getProduct_type_other_name();
            if(productName != null) {
                holder.mProductName.setText(productName);
            }

            String brandName = postsItem.getBrand_other_name();
            Log.i("TAGPRODU", "type " + brandName);
            if(brandName != null) {
                holder.mProductType.setText(brandName);
            }

            String price = postsItem.getPost_price();
            if(price != null) {
                String postQuantity = postsItem.getPost_quantity();
                String rs = mContext.getString(R.string.rs);
                rs = rs + " " + price + "/" + postQuantity;
                holder.mProductPrice.setText(rs);
            }

            String postUnit = postsItem.getPost_unit();
            if(postUnit != null) {
                holder.mProductUnit.setText(postUnit);
            }
        }
    }
    public class TopProductItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.top_product_item_view)
        RelativeLayout mArticleView;

        @BindView(R.id.product_image)
        ImageView mProductImage;

        @BindView(R.id.product_bookmark)
        ImageView mProductBookMark;

        @BindView(R.id.top_product_name)
        TextView mProductName;

        @BindView(R.id.top_product_type)
        TextView mProductType;

        @BindView(R.id.top_product_price)
        TextView mProductPrice;

        @BindView(R.id.top_product_unit)
        TextView mProductUnit;

        public TopProductItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void notifyDataChanged() {
            notifyDataSetChanged();
        }

        @OnClick(R.id.top_product_item_view)
        void onClick(View view) {
            if(mClickListener != null) {
                mClickListener.ItemClicked(view, getAdapterPosition());
            }
        }

    }
}
