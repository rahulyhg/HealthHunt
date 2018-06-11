package in.healthhunt.view.homeScreenView.myHuntsView.myHuntsShopView;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import in.healthhunt.R;
import in.healthhunt.model.articles.commonResponse.CurrentUser;
import in.healthhunt.model.articles.commonResponse.MediaItem;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.presenter.homeScreenPresenter.myHuntPresenter.myHuntsShopPresenter.IMyHuntsProductsPresenter;

/**
 * Created by abhishekkumar on 5/21/18.
 */

public class MyHuntsShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IMyHuntsProductsPresenter IMyHuntsProductsPresenter;
    private Context mContext;
    private ClickListener mClickListener;

    public MyHuntsShopAdapter(Context context, IMyHuntsProductsPresenter myHuntsProductsPresenter) {
        mContext = context;
        IMyHuntsProductsPresenter = myHuntsProductsPresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.my_hunts_product_item_view, parent, false);
        return IMyHuntsProductsPresenter.createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setContent((MyHuntsShopHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        return IMyHuntsProductsPresenter.getCount();
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public interface ClickListener {
        void ItemClicked(View v, int position);
        void onLongClicked(int position);
    }

    public void updateAdapter(){
        notifyDataSetChanged();
    }

    private void setContent(MyHuntsShopHolder holder, int pos) {

        ProductPostItem postsItem = IMyHuntsProductsPresenter.getProduct(pos);
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
                holder.mProductImage.setImageResource(R.drawable.artical);
            }


            String productName = postsItem.getPost_name();
            if(productName != null) {
                holder.mProductName.setText(productName);
            }
            else {
                holder.mProductName.setText("");
            }

            String brandName = postsItem.getCompany_name();
            Log.i("TAGPRODU", "type " + brandName);
            if(brandName != null) {
                holder.mProductType.setText(brandName);
            }
            else {
                holder.mProductType.setText("");
            }

            CurrentUser currentUser = postsItem.getCurrent_user();
            if(currentUser != null) {
                if(!currentUser.isBookmarked()){
                    holder.mBookMark.setColorFilter(null);
                    holder.mBookMark.setImageResource(R.mipmap.ic_bookmark);
                }
                else {
                    holder.mBookMark.setColorFilter(ContextCompat.getColor(mContext, R.color.hh_green_light2), PorterDuff.Mode.SRC_IN);
                }
            }

            String price = postsItem.getPost_price();
            if(price != null) {
                String postQuantity = postsItem.getPost_quantity();
                String rs = mContext.getString(R.string.rs);
                rs = rs + " " + price + "/" + postQuantity;
                holder.mProductPrice.setText(rs);
            }
            else {
                holder.mProductPrice.setText("");
            }

            String postUnit = postsItem.getPost_unit();
            if(postUnit != null) {
                holder.mProductUnit.setText(postUnit);
            }
            else {
                holder.mProductUnit.setText("");
            }
        }
    }
}
