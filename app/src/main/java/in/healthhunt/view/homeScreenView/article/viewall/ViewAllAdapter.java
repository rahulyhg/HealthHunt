package in.healthhunt.view.homeScreenView.article.viewall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import in.healthhunt.R;
import in.healthhunt.model.articles.ArticleParams;
import in.healthhunt.model.articles.articleResponse.ArticlePostItem;
import in.healthhunt.model.articles.articleResponse.CategoriesItem;
import in.healthhunt.model.articles.articleResponse.TagsItem;
import in.healthhunt.model.articles.articleResponse.Title;
import in.healthhunt.model.articles.commonResponse.Author;
import in.healthhunt.model.articles.commonResponse.MediaItem;
import in.healthhunt.model.articles.productResponse.ProductPostItem;
import in.healthhunt.model.utility.HealthHuntUtility;
import in.healthhunt.presenter.homeScreenPresenter.articlePresenter.viewallPresenter.IViewAllPresenter;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by abhishekkumar on 4/27/18.
 */

public class ViewAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IViewAllPresenter IViewAllPresenter;
    private Context mContext;
    private int mType;
    private ClickListener mClickListener;

    public ViewAllAdapter(Context context, IViewAllPresenter viewAllPresenter, int type) {
        mContext = context;
        IViewAllPresenter = viewAllPresenter;
        mType = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("TAg@@","viewType");

        int layout = IViewAllPresenter.getView();
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return IViewAllPresenter.createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mType) {
            case ArticleParams.BASED_ON_TAGS:
            case ArticleParams.LATEST_ARTICLES:
                ArticlePostItem tagsItem = IViewAllPresenter.getArticle(position);
                setArticleContent(((ViewAllArticleHolder) holder), tagsItem);
                break;

            case ArticleParams.LATEST_PRODUCTS_ARTICLES:
                ProductPostItem postItem =  IViewAllPresenter.getProduct(position);
                setProductContent(((ViewAllProductHolder) holder), postItem);
                break;
        }
    }

    private void setArticleContent(ViewAllArticleHolder holder, ArticlePostItem postsItem) {
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
                holder.mArticleImage.setBackgroundResource(R.drawable.artical);
            }


            String categoryName = null;
            List<CategoriesItem> categories = postsItem.getCategories();
            if(categories != null && !categories.isEmpty()){
                categoryName = categories.get(0).getName();
            }

            holder.mCategoryName.setText(categoryName);

            Author author = postsItem.getAuthor();
            if(author != null){
                holder.mAuthorName.setText(author.getName());

                String authorUrl = author.getUrl();
                authorUrl = authorUrl.replace("\n", "");
                Glide.with(mContext)
                        .load(authorUrl)
                        .bitmapTransform(new CropCircleTransformation(mContext))/*.placeholder(R.mipmap.default_profile)*/
                        .into(holder.mAuthorImage);

            }

            Title title = postsItem.getTitle();
            String articleTitle = null;
            if(title != null) {
                articleTitle = title.getRendered();
            }
            holder.mArticleTitle.setText(articleTitle);


            String tagsName = "";
            List<TagsItem> tagItems = postsItem.getTags();
            if(tagItems != null && !tagItems.isEmpty()) {
                for(TagsItem  tagItem: tagItems) {
                    tagsName = tagsName + "#" + tagItem.getName() + "  ";
                }
            }

            holder.mHashTags.setText(tagsName);


            String readingTime = postsItem.getRead_time();
            readingTime = readingTime + " min read";
            holder.mReadingTime.setText(readingTime);

            String date = postsItem.getDate();
            if(date != null) {
                date = HealthHuntUtility.getDateWithFormat(date);
            }
            holder.mArticleDate.setText(date);
        }
    }

    private void setProductContent(ViewAllProductHolder holder, ProductPostItem postsItem) {

        if(postsItem != null) {
            String productName = postsItem.getProduct_type_other_name();
            if (productName != null) {
                holder.mProductName.setText(productName);
            }


            String price = postsItem.getPost_price();
            if (price != null) {
                String postQuantity = postsItem.getPost_quantity();
                String rs = mContext.getString(R.string.rs);
                rs = rs + " " + price + "/" + postQuantity;
                holder.mProductPrice.setText(rs);
            }


            String postUnit = postsItem.getPost_unit();
            if (postUnit != null) {
                holder.mProductUnit.setText(postUnit);
            }


            String url = null;
            List<MediaItem> mediaItems = postsItem.getMedia();
            if(mediaItems != null && !mediaItems.isEmpty()) {
                MediaItem media = mediaItems.get(0);
                Log.i("TAGMedia", "media "+ media);
                if("image".equals(media.getMedia_type())) {
                    url = media.getUrl();
                    Log.i("TAGMedia", "URL "+ url);

                }
            }
            if (url != null) {
                Log.i("TAG11", "productUrl " + url);
                Glide.with(mContext).load(url).placeholder(R.drawable.top_products).into(holder.mProductImage);
            } else {
                holder.mProductImage.setBackgroundResource(R.drawable.top_products);
            }
        }
    }

    @Override
    public int getItemCount() {
        return IViewAllPresenter.getCount(mType);
    }

    public void setClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public interface ClickListener {
        void ItemClicked(View v, int position);
    }

}
