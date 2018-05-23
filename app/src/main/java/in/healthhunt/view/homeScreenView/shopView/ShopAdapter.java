package in.healthhunt.view.homeScreenView.shopView;

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
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.articleResponse.CategoriesItem;
import in.healthhunt.model.articles.articleResponse.Title;
import in.healthhunt.model.articles.commonResponse.CurrentUser;
import in.healthhunt.model.articles.commonResponse.MediaItem;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.shopPresenter.IShopPresenter;

/**
 * Created by abhishekkumar on 4/23/18.
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopViewHolder> {

    private IShopPresenter IShopPresenter;
    private Context mContext;
    private ClickListener mClickListener;

    public ShopAdapter(Context context, IShopPresenter shopPresenter) {
        mContext = context;
        IShopPresenter = shopPresenter;
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shop_item_view, parent, false);
        return IShopPresenter.createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, int position) {
        setContent(holder, position);
    }

    @Override
    public int getItemCount() {
        return IShopPresenter.getCount();
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public interface ClickListener {
        void ItemClicked(View v, int position);
    }

    private void setContent(ShopViewHolder holder, int pos) {

        ArticlePostItem postsItem = IShopPresenter.getArticle(pos);
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
                Glide.with(mContext).load(url).placeholder(R.drawable.artical).into(holder.mArticleImage);
            }
            else {
                holder.mArticleImage.setImageResource(R.drawable.artical);
            }


            String categoryName = null;
            List<CategoriesItem> categories = postsItem.getCategories();
            if(categories != null && !categories.isEmpty()){
                categoryName = categories.get(0).getName();

                holder.mCategoryView.setText(categoryName);
                holder.mCategoryImage.setColorFilter(ContextCompat.getColor(mContext, R.color.hh_blue_light), PorterDuff.Mode.SRC_IN);
                int res = HealthHuntUtility.getCategoryIcon(categoryName);
                if(res != 0){
                    holder.mCategoryImage.setImageResource(res);
                }
                else {
                    holder.mCategoryImage.setImageResource(R.mipmap.ic_fitness);
                }
            }

            Title title = postsItem.getTitle();
            String articleTitle = null;
            if(title != null) {
                articleTitle = title.getRendered();
            }
            holder.mShopContent.setText(articleTitle);

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
        }
    }
}